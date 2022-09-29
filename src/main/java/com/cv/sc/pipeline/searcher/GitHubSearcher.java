package com.cv.sc.pipeline.searcher;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubEntity;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GithubUser;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.ObjectMapperProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.*;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/09/22
 */
public class GitHubSearcher implements Searcher {
    private static GitHubSearcher instance;

    private GitHubSearcher() {
    }

    public static GitHubSearcher getInstance() {
        if (instance == null) {
            instance = new GitHubSearcher();
        }
        return instance;
    }

    public List<GithubUser> getUserSearchResult(String searchTerm) throws HttpClientException, IOException {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, searchTerm);
        params.put(Constants.QUERY_QUALIFIER_IN, Constants.QUERY_QUALIFIER_USER);

        String userRequestUrl= getRequestUrlQuery(GitHubEndpoints.USER_SEARCH_ENDPOINT, params);

        HttpClient userHttpClient = new HttpClient(userRequestUrl,
                Collections.emptyMap(), getHeaders(), HttpMethod.GET);
        HttpResponse userResponse = userHttpClient.exchange();
        String userResponseString = userResponse.parseAsString();
        return extract(userResponseString, GithubUser.class);
    }

    public  <T extends GitHubEntity> List<T>  extract(String userResponseString, Class<T> clazz) throws JsonProcessingException {
        List<T> entityList = new ArrayList<>(10);
        ObjectMapper objectMapperForParsingResult = ObjectMapperProvider.getObjectMapperForParsingResult();
        JsonNode items = objectMapperForParsingResult.readTree(userResponseString).get("items");
        Iterator<JsonNode> iterator = items.iterator();
        while (iterator.hasNext()) {
            JsonNode next = iterator.next();
            String s = next.toString();
            T gitHubEntity = objectMapperForParsingResult.readValue(s, clazz);
            entityList.add(gitHubEntity);
        }
        return entityList;
    }

    public List<GitHubFileSearch> getFileSearchResult(String searchTerm) throws HttpClientException, IOException {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, null);
        params.put(Constants.QUERY_QUALIFIER_FILENAME, searchTerm);

        String fileRequestUrl= getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT, params);

        HttpClient fileHttpClient = new HttpClient(fileRequestUrl,
                Collections.emptyMap(), getHeaders(), HttpMethod.GET);
        HttpResponse fileResponse = fileHttpClient.exchange();
        String fileResponseString = fileResponse.parseAsString();
        return extract(fileResponseString, GitHubFileSearch.class);
    }

    @Override
    public SearchResponse search(Config config) throws HttpClientException, IOException {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setConfig(config);
        if (Objects.nonNull(config.getUserSearchKeywords())) {
            for (String searchTerm : config.getUserSearchKeywords()) {
                searchResponse.addUserSearchResult(Map.of(searchTerm, getUserSearchResult(searchTerm)));
            }
        }

        if (Objects.nonNull(config.getCodeSearchKeywords())) {
            for (String searchTerm : config.getCodeSearchKeywords()) {
                searchResponse.addContentSearch(Map.of(searchTerm, getContentSearchResult(searchTerm)));
            }
        }

        if(Objects.nonNull(config.getFileNames())) {
            for (String searchTerm : config.getFileNames()) {
                searchResponse.addFileSearchResult(Map.of(searchTerm, getFileSearchResult(searchTerm)));
            }
        }
        return searchResponse;
    }

    public List<GitHubContentSearch> getContentSearchResult(String searchTerm) throws HttpClientException, IOException {
        String codeRequestUrl= getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, searchTerm));
        HttpClient codeHttpClient = new HttpClient(codeRequestUrl,
                Collections.emptyMap(), getHeaders(), HttpMethod.GET);
        HttpResponse codeResponse = codeHttpClient.exchange();
        String codeResponseString = codeResponse.parseAsString();
        return  extract(codeResponseString, GitHubContentSearch.class);
    }

    public Map<String, String> getRepoSearchResult(String searchTerm) {

        String repoRequestUrl= getRequestUrlQuery(GitHubEndpoints.REPO_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, searchTerm));

        HttpClient repoHttpClient = new HttpClient(repoRequestUrl,
                Collections.emptyMap(), getHeaders(), HttpMethod.GET);
        HttpResponse repoResponse;
        Map<String, String> responseMap = new HashMap<>();
        try {
            repoResponse = repoHttpClient.exchange();
            String repoResponseString = repoResponse.parseAsString();
            responseMap.put("RepoResult",repoResponseString);
        } catch (HttpClientException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseMap;
    }

    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    public String getRequestUrlQuery(String url, Map<String, String> params){
        StringBuilder requestQueryUrl = new StringBuilder();
        requestQueryUrl.append(url);
        for(String key: params.keySet()) {
            if(key.equals("q")) {
                requestQueryUrl.append("?");
                requestQueryUrl.append(key);
                requestQueryUrl.append("=");
                requestQueryUrl.append(params.get(key));
            } else {
                requestQueryUrl.append("&");
                requestQueryUrl.append(key);
                requestQueryUrl.append(":");
                requestQueryUrl.append(params.get(key));
            }
        }
        return requestQueryUrl.toString();

    }

    public Map<String, String> getHeaders() {
        String authToken =System.getenv(AUTH_TOKEN);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.AUTH_HEADER, authToken);
        headers.put(Constants.ACCEPT_HEADER, Constants.ACCEPT_HEADER_VALUE);
        return headers;
    }
}

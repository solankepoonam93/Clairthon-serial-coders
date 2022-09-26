package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubEntity;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GithubUser;
import com.cv.sc.storage.StorageService;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.ObjectMapperProvider;
import com.cv.sc.util.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class OrchestratorImpl implements Orchestrator{
    private UserUtils userUtils;
    private StorageService storageService;

    public OrchestratorImpl() {
        userUtils = new UserUtils();
        storageService = new DBStorageServiceImpl();
    }


    @Override
    public SearchResponse search(Config config) throws HttpClientException, IOException {

        SearchResponse searchResponse = new SearchResponse();

        for(String searchTerm: config.getUserSearchKeywords()) {
            searchResponse.addUserSearchResult(Map.of(searchTerm, getUserSearchResult(searchTerm)));
        }

        for(String searchTerm: config.getCodeSearchKeywords()) {
            searchResponse.addContentSearch(Map.of(searchTerm, getContentSearchResult(searchTerm)));
        }

        for(String searchTerm: config.getFileNames()) {
            searchResponse.addFileSearchResult(Map.of(searchTerm, getFileSearchResult(searchTerm)));
        }

        // TODO Repo Search
        //Repo Search
        /*//uncomment when need to try for repo search
         Map<String, String> repoSearchResult = getRepoSearchResult(config);*/
        searchResponse = saveSearchResult(searchResponse);
        return searchResponse;
    }

    @Override
    public SearchResponse saveSearchResult(SearchResponse searchResponse) throws UnsupportedEncodingException {
        return (SearchResponse) storageService.save(searchResponse);
    }

    private List<GitHubEntity> getContentSearchResult(String searchTerm) throws HttpClientException, IOException {
        String codeRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, searchTerm));
        HttpClient codeHttpClient = new HttpClient(codeRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse codeResponse = codeHttpClient.exchange();
        String codeResponseString = codeResponse.parseAsString();
        return  extract(codeResponseString, GitHubContentSearch.class);
    }

    private List<GitHubEntity> getUserSearchResult(String searchTerm) throws HttpClientException, IOException {

        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, searchTerm);
        params.put(Constants.QUERY_QUALIFIER_IN, Constants.QUERY_QUALIFIER_USER);

        String userRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.USER_SEARCH_ENDPOINT, params);

        HttpClient userHttpClient = new HttpClient(userRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse userResponse = userHttpClient.exchange();
        String userResponseString = userResponse.parseAsString();
        return extract(userResponseString, GithubUser.class);
    }

    public List<GitHubEntity> extract(String userResponseString, Class<? extends GitHubEntity> clazz) throws JsonProcessingException {
        List<GitHubEntity> entityList = new ArrayList<>(10);
        ObjectMapper objectMapperForParsingResult = ObjectMapperProvider.getObjectMapperForParsingResult();
        JsonNode items = objectMapperForParsingResult.readTree(userResponseString).get("items");
        Iterator<JsonNode> iterator = items.iterator();
        while (iterator.hasNext()) {
            JsonNode next = iterator.next();
            String s = next.toString();
            GitHubEntity gitHubEntity = objectMapperForParsingResult.readValue(s, clazz);
            entityList.add(gitHubEntity);
        }
        return entityList;
    }

    private List<GitHubEntity> getFileSearchResult(String searchTerm) throws HttpClientException, IOException {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, null);
        params.put(Constants.QUERY_QUALIFIER_FILENAME, searchTerm);

        String fileRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT, params);

        HttpClient fileHttpClient = new HttpClient(fileRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse fileResponse = fileHttpClient.exchange();
        String fileResponseString = fileResponse.parseAsString();
        return extract(fileResponseString, GitHubFileSearch.class);
    }

    private Map<String, String> getRepoSearchResult(String searchTerm) {

        String repoRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.REPO_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, searchTerm));

        HttpClient repoHttpClient = new HttpClient(repoRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
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
}

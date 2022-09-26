package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.Config;
import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubEntity;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GithubUser;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.ObjectMapperProvider;
import com.cv.sc.util.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.*;

public class OrchestratorImpl implements Orchestrator{

    private UserUtils userUtils = new UserUtils();

    @Override
    public Map<String, List<GitHubEntity>> search(Config config) throws HttpClientException, IOException {

        Map<String, List<GitHubEntity>> responseMap= new HashMap<>();
        //Code search
        List<GitHubEntity> contentSearchResult = getContentSearchResult(config);
        //User search
        List<GitHubEntity> userSearchResult = getUserSearchResult(config);
        //fileSearch
        List<GitHubEntity> fileSearchResult = getFileSearchResult(config);
        //Repo Search
        /*//uncomment when need to try for repo serach
         Map<String, String> repoSearchResult = getRepoSearchResult(config);*/

        //Package search

        //combine the results of all in one map
        responseMap.put("CodeResult", contentSearchResult);
        responseMap.put("UserResult", userSearchResult);
        responseMap.put("FileResult", fileSearchResult);

        //need to refactor this once model for final result JSON is ready
        return responseMap;
    }

    @Override
    public void saveSearchResult(Map<String, String> searchResponse) {
        //save response to S3
        //Save response to DB

    }

    private List<GitHubEntity> getContentSearchResult(Config config) throws HttpClientException, IOException {
        String codeRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, config.getCodeSearchKeywords()));
        HttpClient codeHttpClient = new HttpClient(codeRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse codeResponse = codeHttpClient.exchange();
        String codeResponseString = codeResponse.parseAsString();
        return  extract(codeResponseString, GitHubContentSearch.class);
    }

    private List<GitHubEntity> getUserSearchResult(Config config) throws HttpClientException, IOException {

        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, config.getUserSearchKeywords());
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

    private List<GitHubEntity> getFileSearchResult(Config config) throws HttpClientException, IOException {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, null);
        params.put(Constants.QUERY_QUALIFIER_FILENAME,config.getCodeSearchKeywords());

        String fileRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT, params);

        HttpClient fileHttpClient = new HttpClient(fileRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse fileResponse = fileHttpClient.exchange();
        String fileResponseString = fileResponse.parseAsString();
        return extract(fileResponseString, GitHubFileSearch.class);
    }

    private Map<String, String> getRepoSearchResult(Config config) {

        String repoRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.REPO_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, config.getRepositoryNames()));

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

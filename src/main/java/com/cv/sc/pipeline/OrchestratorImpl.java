package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.Config;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.UserUtils;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrchestratorImpl implements Orchestrator{

    private UserUtils userUtils = new UserUtils();

    @Override
    public Map<String, String> search(Config config) {

        Map<String, String> responseMap= new HashMap<>();
        //Code search
        Map<String, String> codeSearchResult = getCodeSearchResult(config);
        //User search
        Map<String, String> userSearchResult = getUserSearchResult(config);
        //fileSearch
        Map<String, String> fileSearchResult = getFileSearchResult(config);
        //Repo Search
        /*//uncomment when need to try for repo serach
         Map<String, String> repoSearchResult = getRepoSearchResult(config);*/

        //Package search

        //combine the results of all in one map
        responseMap.putAll(codeSearchResult);
        responseMap.putAll(userSearchResult);
        responseMap.putAll(fileSearchResult);
        /*responseMap.putAll(repoSearchResult);*/
        //need to refactor this once model for final result JSON is ready
        return responseMap;
    }

    @Override
    public void saveSearchResult(Map<String, String> searchResponse) {
        //save response to S3
        //Save response to DB

    }

    private Map<String, String> getCodeSearchResult(Config config) {
        String codeRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, config.getCodeSearchKeywords()));

        HttpClient codeHttpClient = new HttpClient(codeRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse codeResponse;
        Map<String, String> responseMap = new HashMap<>();
        try {
              codeResponse = codeHttpClient.exchange();
              String codeResponseString = codeResponse.parseAsString();
              System.out.println(codeResponseString);
              responseMap.put("CodeResult",codeResponseString); //Todo need to put only selected fields
          } catch (HttpClientException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
        return responseMap;
    }

    private Map<String, String> getUserSearchResult(Config config) {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, config.getUserSearchKeywords());
        params.put(Constants.QUERY_QUALIFIER_IN, Constants.QUERY_QUALIFIER_USER);

        String userRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.USER_SEARCH_ENDPOINT, params);

        HttpClient userHttpClient = new HttpClient(userRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse userResponse;
        Map<String, String> responseMap = new HashMap<>();
        try {
            userResponse = userHttpClient.exchange();
            String userResponseString = userResponse.parseAsString();
            System.out.println(userResponseString);
            responseMap.put("UserResult",userResponseString);
        } catch (HttpClientException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseMap;
    }

    private Map<String, String> getFileSearchResult(Config config) {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, null);
        params.put(Constants.QUERY_QUALIFIER_FILENAME,config.getCodeSearchKeywords());

        String fileRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT, params);

        HttpClient fileHttpClient = new HttpClient(fileRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse fileResponse;
        Map<String, String> responseMap = new HashMap<>();
        try {
            fileResponse = fileHttpClient.exchange();
            String fileResponseString = fileResponse.parseAsString();
            System.out.println(fileResponseString);
            responseMap.put("FileResult",fileResponseString);
        } catch (HttpClientException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseMap;
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
            System.out.println(repoResponseString);
            responseMap.put("RepoResult",repoResponseString);
        } catch (HttpClientException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseMap;
    }
}

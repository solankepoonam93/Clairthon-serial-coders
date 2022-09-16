package com.cv.sc.pipeline;

import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpClientException;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.models.Config;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.UserUtils;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrchestratorImpl implements Orchestrator{

    UserUtils userUtils = new UserUtils();

    @Override
    public Map<String, String> search(Config config) {

        Map<String, String> responseMap= new HashMap<>();
        //Code search
        Map<String, String> codeSearchResult = getCodeSearchResult(config);
        //User search
        Map<String, String> userSearchResult = getUserSearchResult(config);
        //Repo Search
        //Package search

        //combine the results of all in one map
        responseMap.putAll(codeSearchResult);
        responseMap.putAll(userSearchResult);
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
}

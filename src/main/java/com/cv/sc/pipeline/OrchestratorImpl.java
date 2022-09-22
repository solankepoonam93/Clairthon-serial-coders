package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.util.Constants;
import com.cv.sc.util.GitHubEndpoints;
import com.cv.sc.util.UserUtils;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class OrchestratorImpl implements Orchestrator{

    private UserUtils userUtils = new UserUtils();

    @Override
    public SearchResponse search(Config config) {

        JSONObject responseMap = new JSONObject();
        //Code search
        JSONObject codeSearchResult = getCodeSearchResult(config);
        //User search
        JSONObject userSearchResult = getUserSearchResult(config);
        //fileSearch
        JSONObject fileSearchResult = getFileSearchResult(config);
        //Repo Search
        /*//uncomment when need to try for repo search
         JSONObject repoSearchResult = getRepoSearchResult(config);*/
        //Package search

        //combine the results of all in one object
        SearchResponse searchResponse = new SearchResponse();
        JSONObject combinedJson = new JSONObject();
        combinedJson.put(Constants.JSON_CODE,codeSearchResult);
        combinedJson.put(Constants.JSON_USER,userSearchResult);
        combinedJson.put(Constants.JSON_FILE,fileSearchResult);
        /*combinedJson.put(Constants.JSON_REPO,repoSearchResult);*/
        //PackageResult
        searchResponse.setResponse(combinedJson);
        return searchResponse;
    }

    @Override
    public void saveSearchResult(Map<String, String> searchResponse) {
        //save response to S3
        //Save response to DB

    }

    private JSONObject getCodeSearchResult(Config config) {
        String codeRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, config.getCodeSearchKeywords()));

        HttpClient codeHttpClient = new HttpClient(codeRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse codeResponse;
        JSONObject codeResponseJson;
        try {
              codeResponse = codeHttpClient.exchange();
              String codeResponseString = codeResponse.parseAsString();
              codeResponseJson = getCodeResponseJson(codeResponseString);
              codeResponseJson.put(Constants.JSON_CODE_COMPLETE_RESPONSE,codeResponseString);
          } catch (HttpClientException | IOException e) {
              throw new RuntimeException(e);
          }
        return codeResponseJson;
    }

    private JSONObject getUserSearchResult(Config config) {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, config.getUserSearchKeywords());
        params.put(Constants.QUERY_QUALIFIER_IN, Constants.QUERY_QUALIFIER_USER);

        String userRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.USER_SEARCH_ENDPOINT, params);

        HttpClient userHttpClient = new HttpClient(userRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse userResponse;
        JSONObject userResponseJson = new JSONObject();
        try {
            userResponse = userHttpClient.exchange();
            String userResponseString = userResponse.parseAsString();
            userResponseJson = getUserResponseJson(userResponseString);
            userResponseJson.put(Constants.JSON_USER_COMPLETE_RESPONSE,userResponseString);
        } catch (HttpClientException | IOException e) {
            throw new RuntimeException(e);
        }
        return userResponseJson;
    }

    private JSONObject getFileSearchResult(Config config) {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.QUERY, null);
        params.put(Constants.QUERY_QUALIFIER_FILENAME,config.getCodeSearchKeywords());

        String fileRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.CODE_SEARCH_ENDPOINT, params);

        HttpClient fileHttpClient = new HttpClient(fileRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse fileResponse;
        JSONObject fileResponseJson = new JSONObject();
        try {
            fileResponse = fileHttpClient.exchange();
            String fileResponseString = fileResponse.parseAsString();
            fileResponseJson = getFileResponseJson(fileResponseString);
            fileResponseJson.put(Constants.JSON_FILE_COMPLETE_RESPONSE,fileResponseString);
        } catch (HttpClientException | IOException e) {
            throw new RuntimeException(e);
        }
        return fileResponseJson;
    }

    private JSONObject getRepoSearchResult(Config config) {

        String repoRequestUrl= userUtils.getRequestUrlQuery(GitHubEndpoints.REPO_SEARCH_ENDPOINT,
                Map.of(Constants.QUERY, config.getRepositoryNames()));

        HttpClient repoHttpClient = new HttpClient(repoRequestUrl,
                Collections.emptyMap(), userUtils.getHeaders(), HttpMethod.GET);
        HttpResponse repoResponse;
        JSONObject repoResponseJson = new JSONObject();
        try {
            repoResponse = repoHttpClient.exchange();
            String repoResponseString = repoResponse.parseAsString();
            repoResponseJson = getRepoResponseJson(repoResponseString);
            repoResponseJson.put(Constants.JSON_REPO_COMPLETE_RESPONSE,repoResponseString);
        } catch (HttpClientException | IOException e) {
            throw new RuntimeException(e);
        }
        return repoResponseJson;
    }

    private JSONObject getCodeResponseJson(String codeResponseString) {
        JSONObject jsonObject =(JSONObject) JSONValue.parse(codeResponseString);
        JSONArray items = (JSONArray) jsonObject.get(Constants.JSON_ITEMS);
        JSONArray itemArray = new JSONArray();
        Map<String, String> itemMap = new HashMap<>();
        JSONObject responseJson = new JSONObject();
        for (Object item : items) {
            itemMap.clear();
            JSONObject itemJson = (JSONObject) item;
            itemMap.put(Constants.JSON_NAME, itemJson.get(Constants.JSON_NAME).toString());
            itemMap.put(Constants.JSON_PATH, itemJson.get(Constants.JSON_PATH).toString());
            itemMap.put(Constants.JSON_REPOSITORY, ((JSONObject) itemJson.get(Constants.JSON_REPOSITORY))
                    .get(Constants.JSON_FULL_NAME).toString());
            itemMap.put(Constants.JSON_OWNER, ((JSONObject) ((JSONObject) itemJson.get(Constants.JSON_REPOSITORY))
                    .get(Constants.JSON_OWNER)).get(Constants.JSON_LOGIN).toString());
            itemArray.add(itemMap);
        }
        responseJson.put(Constants.JSON_CODE_RESPONSE_ITEMS,itemArray);
        return responseJson;
    }
    private JSONObject getUserResponseJson(String userResponseString) {
        JSONObject jsonObject =(JSONObject) JSONValue.parse(userResponseString);
        JSONArray items = (JSONArray) jsonObject.get(Constants.JSON_ITEMS);
        JSONArray itemArray = new JSONArray();
        Map<String, String> itemMap = new HashMap<>();
        JSONObject responseJson = new JSONObject();
        for (Object item : items) {
            itemMap.clear();
            JSONObject itemJson = (JSONObject) item;
            itemMap.put(Constants.JSON_LOGIN, itemJson.get(Constants.JSON_LOGIN).toString());
            itemMap.put(Constants.JSON_TYPE, itemJson.get(Constants.JSON_TYPE).toString());
            itemArray.add(itemMap);
        }
        responseJson.put(Constants.JSON_USER_RESPONSE_ITEMS,itemArray);
        return responseJson;
    }

    private JSONObject getFileResponseJson(String fileResponseString) {
        JSONObject jsonObject =(JSONObject) JSONValue.parse(fileResponseString);
        JSONArray items = (JSONArray) jsonObject.get(Constants.JSON_ITEMS);
        JSONArray itemArray = new JSONArray();
        Map<String, String> itemMap = new HashMap<>();
        JSONObject responseJson = new JSONObject();
        for (Object item : items) {
            itemMap.clear();
            JSONObject itemJson = (JSONObject) item;
            itemMap.put(Constants.JSON_NAME, itemJson.get(Constants.JSON_NAME).toString());
            itemMap.put(Constants.JSON_PATH, itemJson.get(Constants.JSON_PATH).toString());
            itemMap.put(Constants.JSON_REPOSITORY, ((JSONObject) itemJson.get(Constants.JSON_REPOSITORY))
                    .get(Constants.JSON_FULL_NAME).toString());
            itemMap.put(Constants.JSON_OWNER, ((JSONObject) ((JSONObject) itemJson.get(Constants.JSON_REPOSITORY))
                    .get(Constants.JSON_OWNER)).get(Constants.JSON_LOGIN).toString());
            itemArray.add(itemMap);
        }
        responseJson.put(Constants.JSON_FILE_RESPONSE_ITEMS,itemArray);
        return responseJson;
    }


    private JSONObject getRepoResponseJson(String repoResponseString) {
        JSONObject jsonObject =(JSONObject) JSONValue.parse(repoResponseString);
        JSONArray items = (JSONArray) jsonObject.get(Constants.JSON_ITEMS);
        Map<String, String> itemMap = new HashMap<>();
        JSONObject responseJson = new JSONObject();
        JSONArray itemArray = new JSONArray();
        for (Object item : items) {
            itemMap.clear();
            JSONObject itemJson = (JSONObject) item;
            itemMap.put(Constants.JSON_FULL_NAME, itemJson.get(Constants.JSON_FULL_NAME).toString());
            itemMap.put(Constants.JSON_OWNER, ((JSONObject) itemJson.get(Constants.JSON_OWNER))
                    .get(Constants.JSON_LOGIN).toString());
            itemMap.put(Constants.JSON_CREATED_AT, itemJson.get(Constants.JSON_CREATED_AT).toString());
            itemMap.put(Constants.JSON_PUSHED_AT, itemJson.get(Constants.JSON_PUSHED_AT).toString());
            itemArray.add(itemMap);
        }
        responseJson.put(Constants.JSON_REPO_RESPONSE_ITEMS,itemArray);
        return responseJson;
    }
}

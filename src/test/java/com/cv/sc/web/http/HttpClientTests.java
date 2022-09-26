package com.cv.sc.web.http;

import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.web.WebTests;
import com.google.api.client.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import java.util.Collections;

/**
 * <br>Created By: bhushan.karmarkar12@gmail.com<br>
 * Date: 12/09/22
 */
public class HttpClientTests extends WebTests {

    @Test
    public void testHttpClient() {
        HttpClient httpClient = getHttpClient(getQueryParamForUserSearch(),
                Collections.emptyMap(), getHeaderMapContainingGitToken(), HttpMethod.GET);
        Assert.assertNotNull(httpClient);
    }

    @Test
    public void testSearchUser() throws Exception {
        HttpClient httpClient = getHttpClient(getQueryParamForUserSearch(),
                Collections.emptyMap(),
                getHeaderMapContainingGitToken(),
                HttpMethod.GET);
        HttpResponse httpResponse = httpClient.exchange();
        Assert.assertEquals(200, httpResponse.getStatusCode());

        String userSearchJson = httpResponse.parseAsString();
        Assert.assertNotNull(userSearchJson);
        Assert.assertTrue(userSearchJson.contains("solankepoonam"));
        System.out.println(userSearchJson);
    }



    /*@Test
    public void testCodeSearch() throws Exception {

        HttpClient httpClient = getHttpClient(getQueryParamForCodeSearch(),
                Collections.emptyMap(),
                getHeaderMapContainingToken(),
                HttpMethod.GET);
        HttpResponse codeResponse = httpClient.exchange();
        Assert.assertEquals(200, codeResponse.getStatusCode());

        String userSearchJson = codeResponse.parseAsString();
        Assert.assertNotNull(userSearchJson);
        Assert.assertTrue(userSearchJson.contains("CountAboveSixty"));
        System.out.println(userSearchJson);
    }
    private String getQueryParamForCodeSearch() {
        return GitHubEndpoints.CODE_SEARCH_ENDPOINT + "?q=public class CountAboveSixty";
    }

    @Test
    public void testRepoSearch() throws Exception {
        HttpClient httpClient = getHttpClient(getQueryParamForRepoSearch(),
                Collections.emptyMap(),
                getHeaderMapContainingToken(),
                HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        Assert.assertNotNull(response);

        String responseJson = response.parseAsString();
        Assert.assertTrue(responseJson.contains("AmrutaChichani"));
        Assert.assertTrue(response.isSuccessStatusCode());
        System.out.println(responseJson);
    }
    private String getQueryParamForRepoSearch() {
       return GitHubEndpoints.REPO_SEARCH_ENDPOINT + "?q=backend-bookstore user:AmrutaChichani";

    }

    @Test
    public void testFileSearch() throws Exception {
        HttpClient httpClient = getHttpClient(getFileSearchQuery(),
                Collections.emptyMap(),
                getHeaderMapContainingToken(),
                HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        Assert.assertNotNull(response);

        String responseJson = response.parseAsString();
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        Assert.assertTrue(responseJson.contains("CountAboveSixty"));
        System.out.println(responseJson);
    }

    private String getFileSearchQuery(){
        return GitHubEndpoints.CODE_SEARCH_ENDPOINT+"?q= filename:CountAboveSixty";
    }

    @Test
    public void testCollectiveSearch() throws Exception {
        Map<String, String> queries = getQuerysForAll("CreationPathController");
        Map<String,HttpResponse> responses = new HashMap<>();
        HttpClient httpClient;
        for( Map.Entry<String, String> entry : queries.entrySet()) {
            httpClient = getHttpClient(entry.getValue(),
                    Collections.emptyMap(), getHeaderMapContainingToken(), HttpMethod.GET);
            responses.put(entry.getKey(), httpClient.exchange());
        }
        Assert.assertNotNull(responses);
        Assert.assertFalse(responses.isEmpty());
        for( Map.Entry<String, HttpResponse> entry : responses.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().parseAsString());
        }
    }

    private Map<String, String> getQuerysForAll(String keyword) {
        Map<String, String> queryMap= new HashMap<>();
        queryMap.put("code",GitHubEndpoints.CODE_SEARCH_ENDPOINT + "?q=" + keyword);
        queryMap.put("user", GitHubEndpoints.USER_SEARCH_ENDPOINT + "?q=" + keyword + " in:user");
        queryMap.put("repo", GitHubEndpoints.REPO_SEARCH_ENDPOINT + "?q=" + keyword);
        queryMap.put("filename", GitHubEndpoints.CODE_SEARCH_ENDPOINT + "?q= filename:" + keyword);
        return queryMap;
    }*/
}
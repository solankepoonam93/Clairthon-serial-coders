package com.cv.sc.http;

import com.cv.sc.util.GitHubEndpoints;
import com.google.api.client.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>Created By: bhushan.karmarkar12@gmail.com<br>
 * Date: 12/09/22
 */
public class HttpClientTest {

    @Test
    public void testHttpClient() {
        HttpClient httpClient = getHttpClient(GitHubEndpoints.USER_SEARCH_ENDPOINT, getQueryParamForUserSearch(), getHeaderMapContainingToken(), HttpMethod.GET);
        Assert.assertNotNull(httpClient);
    }

    @Test
    public void testSearchUser() throws Exception {
        HttpClient httpClient = getHttpClient(GitHubEndpoints.USER_SEARCH_ENDPOINT, getQueryParamForUserSearch(), getHeaderMapContainingToken(), HttpMethod.GET);
        HttpResponse httpResponse = httpClient.exchange();
        Assert.assertEquals(200, httpResponse.getStatusCode());

        String userSearchJson = httpResponse.parseAsString();
        Assert.assertNotNull(userSearchJson);
        Assert.assertTrue(userSearchJson.contains("solankepoonam"));
        System.out.println(userSearchJson);
    }

    private HttpClient getHttpClient(String url, Map<String, String> queryParam, Map<String, String> headers, HttpMethod httpMethod) {
        HttpClientBuilder httpClientBuilder = new HttpClientBuilder();
        return httpClientBuilder.url(url)
                .queryParams(queryParam)
                .headers(headers)
                .httpMethod(httpMethod)
                .build();
    }

    private Map<String, String> getQueryParamForUserSearch() {
        Map<String, String> params = new HashMap<>();
        params.put("q", "solankepoonam in:user");
        return params;
    }

    private Map<String, String> getHeaderMapContainingToken() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/vnd.github+json");
        headers.put("Authorization", "Bearer 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"); //TODO pass as env var
        return headers;
    }

    @Test
    public void testCodeSearch() throws Exception {
        HttpClient httpClient = getHttpClient(GitHubEndpoints.CODE_SEARCH_ENDPOINT/*+"?q=package com.test.java8"*/,
                getQueryParamForCodeSearch(),
                getHeaderMapContainingToken(),
                HttpMethod.GET);
        HttpResponse codeResponse = httpClient.exchange();
        Assert.assertEquals(200, codeResponse.getStatusCode());

        String userSearchJson = codeResponse.parseAsString();
        Assert.assertNotNull(userSearchJson);
        Assert.assertTrue(userSearchJson.contains("HttpClient"));
        System.out.println(userSearchJson);
    }
    private Map<String , String> getQueryParamForCodeSearch() {
        Map<String, String> param = new HashMap<>();
        param.put("q", "public HttpClient");
        return param;
    }

    @Test
    public void testRepoSearch() throws Exception {
        HttpClient httpClient = getHttpClient(GitHubEndpoints.REPO_SEARCH_ENDPOINT,
                getQueryParamForRepoSearch(),
                getHeaderMapContainingToken(),
                HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        Assert.assertNotNull(response);

        String responseJson = response.parseAsString();
        Assert.assertTrue(responseJson.contains("AmrutaChichani"));
        Assert.assertTrue(response.isSuccessStatusCode());
        System.out.println(responseJson);
    }
    private Map<String , String> getQueryParamForRepoSearch() {
        Map<String, String> param = new HashMap<>();
        param.put("q","backend-bookstore user:AmrutaChichani");
        return param;
    }
}
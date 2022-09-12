package com.cv.sc.http;

import com.cv.sc.util.GitHubEndpoints;
import com.google.api.client.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
        headers.put("Authorization: Bearer ", "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"); //TODO pass as env var
        return headers;
    }
}
package com.cv.sc.web;

import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpClientBuilder;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.util.GitHubEndpoints;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 21/09/22
 */
public abstract class WebTests {
    protected String authToken = System.getenv("AUTH_TOKEN");
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected HttpClient getHttpClient(String url, Map<String, String> queryParam, Map<String, String> headers, HttpMethod httpMethod) {
        HttpClientBuilder httpClientBuilder = new HttpClientBuilder();
        return httpClientBuilder.url(url)
                .queryParams(queryParam)
                .headers(headers)
                .httpMethod(httpMethod)
                .build();
    }

    protected String getQueryParamForUserSearch() {
        return  GitHubEndpoints.USER_SEARCH_ENDPOINT + "?q=solankepoonam in:user";
    }

    protected Map<String, String> getHeaderMapContainingGitToken() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/vnd.github+json");
        headers.put("Authorization","Bearer "+ authToken);
        return headers;
    }
}

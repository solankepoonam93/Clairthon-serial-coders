package com.cv.sc.http;

import java.util.Map;

/**
 * <br>Created By: bhushan.karmarkar12@gmail.com<br>
 * Date: 12/09/22
 */
public class HttpClientBuilder {

    private String url;
    private Map<String, String> queryParams;
    private Map<String, String> headers;
    private HttpMethod httpMethod;

    public HttpClientBuilder url(String url) {
        this.url = url;
        return this;
    }
    public HttpClientBuilder queryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }
    public HttpClientBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }
    public HttpClientBuilder httpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public HttpClient build() {
        return new HttpClient(url, queryParams, headers, httpMethod);
    }
}

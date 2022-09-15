package com.cv.sc.http;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.util.Map;

/**
 * Generic HTTP client to invoke the url and get response back.
 * <br>Created By: bhushan.karmarkar12@gmail.com<br>
 * Date: 12/09/22
 */
public class HttpClient {

    private String url;
    private Map<String, String> queryParams;
    private Map<String, String> headers;
    private HttpMethod httpMethod;

    private HttpRequest httpRequest;

    public HttpClient(String url, Map<String, String> queryParams, Map<String, String> headers, HttpMethod httpMethod) {
        this.url = url;
        this.queryParams = queryParams;
        this.headers = headers;
        this.httpMethod = httpMethod;
    }

    public HttpResponse exchange() throws HttpClientException, IOException {
        httpRequest = prepareHttpRequest();
        return httpRequest.execute();
    }
    private void setHeaders(HttpRequest httpRequest) {
        if(headers !=null && !headers.isEmpty()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            for(Map.Entry<String, String> entry: headers.entrySet()) {
                if(entry.getValue()!=null) {
                    if(entry.getKey().contains("Authorization")) {
                        //httpHeaders.setAuthorization("Authorization: Bearer "+ entry.getValue());
                        httpHeaders.set("Authorization","Bearer "+ entry.getValue());
                    } else {
                        httpHeaders.set(entry.getKey(), entry.getValue());
                    }
                }
            }
            httpRequest.setHeaders(httpHeaders);
        }
    }

    private HttpRequest prepareHttpRequest() throws HttpClientException, IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        GenericUrl genericUrl = new GenericUrl(url);
        if(httpMethod.equals(HttpMethod.GET)) {
            for(Map.Entry<String, String> entry: queryParams.entrySet()) {
                if(entry.getValue()!=null) {
                    genericUrl.put(entry.getKey(), entry.getValue());
                }
            }
            httpRequest = requestFactory.buildGetRequest(genericUrl);
        } else if(httpMethod.equals(HttpMethod.POST)) {
            httpRequest = requestFactory.buildPostRequest(new GenericUrl(url), new UrlEncodedContent(queryParams));
        }

        if(httpRequest == null) {
            throw new HttpClientException("HTTP Method not supported!");
        }
        setHeaders(httpRequest);
        return httpRequest;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

}

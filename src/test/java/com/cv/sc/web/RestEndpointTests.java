package com.cv.sc.web;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.APIResponse;
import com.cv.sc.model.SearchResult;
import com.cv.sc.web.controller.impl.BasicAuthenticationController;
import com.cv.sc.web.controller.impl.EntityController;
import com.google.api.client.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 21/09/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EntityController.class, BasicAuthenticationController.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class RestEndpointTests extends WebTests {
    @Test
    public void testAuthentication() throws HttpClientException, IOException {
        APIResponse apiResponse = getToken();
        Assert.assertNotNull(apiResponse.getResponse()); // token granted
        Assert.assertEquals(apiResponse.getResponseStatus(), HttpStatus.OK);
    }

    private APIResponse getToken() throws HttpClientException, IOException {
        HttpClient httpClient = getHttpClient(getAuthenticationUrl(),
                Collections.emptyMap(), getHeaderMapContainingCredentials(), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        APIResponse apiResponse = objectMapper.readValue(response.parseAsString(), APIResponse.class);
        return apiResponse;
    }

    @Test
    public void testFetch() throws HttpClientException, IOException {
        String token = (String) getToken().getResponse();
        HttpClient httpClient = getHttpClient(getFetchUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        SearchResult searchResult = objectMapper.readValue(response.parseAsString(), SearchResult.class);
        Assert.assertNotNull(searchResult);
        Assert.assertTrue(searchResult.getId().equals(1L));
        Assert.assertEquals("TEST_CONFIG", searchResult.getSearchParent().getConfig().getConfigName());
    }
    
    protected Map<String, String> getHeaderMapContainingCredentials() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization","Basic "+ Base64.getEncoder().encodeToString("admin:bkpune".getBytes(StandardCharsets.UTF_8)));
        return headers;
    }

    protected Map<String, String> getHeaderMapContainingSCToken(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization","Bearer "+ token);
        return headers;
    }
    private String getAuthenticationUrl() {
        return "http://localhost:8080/auth/basic";
    }

    private String getFetchUrl() {
        return "http://localhost:8080/dao/fetch/SearchResult/1";
    }
}

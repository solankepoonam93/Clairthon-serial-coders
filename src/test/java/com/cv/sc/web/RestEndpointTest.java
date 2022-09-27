package com.cv.sc.web;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.APIResponse;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResult;
import com.cv.sc.web.controller.impl.BasicAuthenticationController;
import com.cv.sc.web.controller.impl.EntityController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 21/09/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EntityController.class, BasicAuthenticationController.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class RestEndpointTest extends WebTests {
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
        String responseString = fetchEntity(token);
        SearchResult searchResult = objectMapper.readValue(responseString, SearchResult.class);
        Assert.assertNotNull(searchResult);
        Assert.assertTrue(searchResult.getId().equals(1L));
        Assert.assertEquals("TEST_CONFIG", searchResult.getSearchParent().getConfig().getConfigName());
    }

    @Test
    public void testFetchAll() throws HttpClientException, IOException {
        String token = (String) getToken().getResponse();
        HttpClient httpClient = getHttpClient(fetchAllUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        List<Config> configList = new ObjectMapper().readValue(response.parseAsString(), new TypeReference<ArrayList < Config >>()
        {
        });
        Assert.assertNotNull(configList);
        Assert.assertTrue(configList.size()>0);
        Assert.assertEquals("TEST_CONFIG", configList.get(0).getConfigName());
    }

    @Test
    public void testUpdate() throws HttpClientException, IOException {
        String token = (String) getToken().getResponse();
        String responseString = fetchEntity(token);

        SearchResult searchResult = objectMapper.readValue(responseString, SearchResult.class);
        searchResult.setQueryUrl("http://test_updated_query_url");
        searchResult.setJsonResult("Updated Result inside TEST");

        HttpClient httpClient = getHttpClient(getUpdateUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.POST);

        httpClient.setContent(objectMapper.writeValueAsString(searchResult));
        HttpResponse exchange = httpClient.exchange();
        SearchResult searchResultUpdated = objectMapper.readValue(exchange.parseAsString(), SearchResult.class);

        Assert.assertTrue(searchResultUpdated.getId().equals(1L));
        Assert.assertEquals("TEST_CONFIG", searchResultUpdated.getSearchParent().getConfig().getConfigName());
        Assert.assertTrue(searchResultUpdated.getJsonResult().equals("Updated Result inside TEST"));
        Assert.assertTrue(searchResultUpdated.getQueryUrl().equals("http://test_updated_query_url"));
    }

    private String fetchEntity(String token) throws HttpClientException, IOException {
        HttpClient httpClient = getHttpClient(getFetchUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        return response.parseAsString();
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
        return "http://localhost:8090/auth/basic";
    }

    private String getFetchUrl() {
        return "http://localhost:8090/dao/fetch/SearchResult/1";
    }

    private String fetchAllUrl() {
        return "http://localhost:8090/dao/fetchAll/Config";
    }

    private String getUpdateUrl() {
        return "http://localhost:8090/dao/update/SearchResult";
    }
}

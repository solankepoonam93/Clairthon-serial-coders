package com.cv.sc.web;

import com.cv.sc.cache.TokenCache;
import com.cv.sc.exception.HttpClientException;
import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.model.APIResponse;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.SearchResult;
import com.cv.sc.web.controller.impl.BasicAuthenticationController;
import com.cv.sc.web.controller.impl.EntityController;
import com.cv.sc.web.controller.impl.SearchController;
import com.cv.sc.web.filter.AuthenticationFilter;
import com.cv.sc.web.filter.FilterConfiguration;
import com.cv.sc.web.listener.SCApplicationListener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 21/09/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchController.class, SCApplicationListener.class, FilterConfiguration.class, AuthenticationFilter.class, EntityController.class, BasicAuthenticationController.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class RestEndpointTest extends WebTests {

    private String validCred= "admin:bkpune";

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    @Qualifier("authenticationFilter")
    private Filter authenticationFilter;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(authenticationFilter, "/*").build();
    }

    @Test
    public void testAuthenticationPositiveCase() throws HttpClientException, IOException {
        APIResponse apiResponse = getToken(validCred);
        Assert.assertNotNull(apiResponse.getResponse()); // token granted
        Assert.assertEquals(HttpStatus.OK, apiResponse.getResponseStatus());
    }

    @Test
    public void testAuthenticationNegativeCase1() throws HttpClientException, IOException {
        APIResponse apiResponse = getToken("admin:poonam");
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, apiResponse.getResponseStatus());
    }

    @Test
    public void logoutTest() throws HttpClientException, IOException {
        APIResponse apiResponse = getToken(validCred);
        String token = apiResponse.getResponse().toString();
        TokenCache.removeToken(token);
        Assert.assertFalse(TokenCache.isTokenPresent(token));
    }

    private APIResponse getToken(String credentials) throws HttpClientException, IOException {
        HttpClient httpClient = getHttpClient(getAuthenticationUrl(),
                Collections.emptyMap(), getHeaderMapContainingCredentials(credentials), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        APIResponse apiResponse = objectMapper.readValue(response.parseAsString(), APIResponse.class);
        return apiResponse;
    }

    @Test
    public void testFetch() throws HttpClientException, IOException {
        String token = (String) getToken(validCred).getResponse();
        String responseString = fetchEntity(token);
        SearchResult searchResult = objectMapper.readValue(responseString, SearchResult.class);
        Assert.assertNotNull(searchResult);
        Assert.assertEquals(1L, (long)searchResult.getId());
        Assert.assertEquals("TEST_CONFIG", searchResult.getSearchParent().getConfig().getConfigName());
    }

    @Test
    public void testFetchAll() throws HttpClientException, IOException {
        String token = (String) getToken(validCred).getResponse();
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
        String token = (String) getToken(validCred).getResponse();
        String responseString = fetchEntity(token);

        SearchResult searchResult = objectMapper.readValue(responseString, SearchResult.class);
        searchResult.setQueryUrl("http://test_updated_query_url");
        searchResult.setJsonResult("Updated Result inside TEST");

        HttpClient httpClient = getHttpClient(getUpdateUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.POST);

        httpClient.setContent(objectMapper.writeValueAsString(searchResult));
        HttpResponse exchange = httpClient.exchange();
        SearchResult searchResultUpdated = objectMapper.readValue(exchange.parseAsString(), SearchResult.class);

        Assert.assertEquals(1L, (long)searchResultUpdated.getId());
        Assert.assertEquals("TEST_CONFIG", searchResultUpdated.getSearchParent().getConfig().getConfigName());
        Assert.assertEquals("Updated Result inside TEST", searchResultUpdated.getJsonResult());
        Assert.assertEquals("http://test_updated_query_url", searchResultUpdated.getQueryUrl());
    }


    @Test
    public void testSearch() throws HttpClientException, IOException {
        HttpClient httpClient = getHttpClient(getSearchUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken((String) getToken(validCred).getResponse()), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        SearchResponse searchResponse = objectMapper.readValue(response.parseAsString(), SearchResponse.class);
        Assert.assertNotNull(searchResponse.getId());
        Assert.assertNotNull(searchResponse.getFileSearchJsonResultString());
        Assert.assertTrue(searchResponse.getUserSearchResults().size() > 0);
    }

    private String getSearchUrl() {
        return "http://localhost:8090/search/config/1?test=true"; // test = 1 for not to make actual calls
    }

    private String fetchEntity(String token) throws HttpClientException, IOException {
        HttpClient httpClient = getHttpClient(getFetchUrl(),
                Collections.emptyMap(), getHeaderMapContainingSCToken(token), HttpMethod.GET);
        HttpResponse response = httpClient.exchange();
        return response.parseAsString();
    }

    protected Map<String, String> getHeaderMapContainingCredentials(String credentials) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization","Basic "+ Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8)));
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

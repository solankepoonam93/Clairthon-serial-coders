package com.cv.sc.web.http;

import com.cv.sc.http.HttpClient;
import com.cv.sc.http.HttpMethod;
import com.cv.sc.util.GitHubEndpoints;
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
    public void testCodeSearch() throws Exception {

        HttpClient httpClient = getHttpClient(getQueryParamForCodeSearch(),
                Collections.emptyMap(),
                getHeaderMapContainingGitToken(),
                HttpMethod.GET);
        Assert.assertNotNull(httpClient);
    }

    private String getQueryParamForCodeSearch() {
        return GitHubEndpoints.CODE_SEARCH_ENDPOINT + "?q=public class sortByMarksAsc";
    }
}
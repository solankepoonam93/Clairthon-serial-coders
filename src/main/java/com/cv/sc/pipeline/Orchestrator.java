package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Orchestrator {
    public SearchResponse search(Config config) throws HttpClientException, IOException;
    public SearchResponse saveSearchResult(SearchResponse searchResponse) throws UnsupportedEncodingException;
}

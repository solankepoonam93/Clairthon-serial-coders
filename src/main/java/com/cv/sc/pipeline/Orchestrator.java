package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;

import java.io.IOException;
import java.util.Map;

public interface Orchestrator {
    public SearchResponse search(Config config) throws HttpClientException, IOException;
    public void saveSearchResult(Map<String, String> searchResponse);
}

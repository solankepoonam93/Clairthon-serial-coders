package com.cv.sc.pipeline;

import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;

import java.util.Map;

public interface Orchestrator {
    public SearchResponse search(Config config);
    public void saveSearchResult(Map<String, String> searchResponse);
}

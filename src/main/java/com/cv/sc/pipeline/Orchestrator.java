package com.cv.sc.pipeline;

import com.cv.sc.models.Config;

import java.util.Map;

public interface Orchestrator {
    public Map<String, String> search(Config config);
    public void saveSearchResult(Map<String, String> searchResponse);
}

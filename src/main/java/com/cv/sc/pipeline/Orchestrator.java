package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Orchestrator {
    public SearchResponse search(Config config);
    public void saveSearchResult(Map<String, String> searchResponse);
}

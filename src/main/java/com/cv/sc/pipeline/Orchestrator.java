package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.github.GitHubEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Orchestrator {
    public Map<String, List<GitHubEntity>> search(Config config) throws HttpClientException, IOException;
    public void saveSearchResult(Map<String, String> searchResponse);
}

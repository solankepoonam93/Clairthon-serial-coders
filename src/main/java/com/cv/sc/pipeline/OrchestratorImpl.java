package com.cv.sc.pipeline;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.pipeline.searcher.GitHubSearcher;
import com.cv.sc.pipeline.searcher.Searcher;
import com.cv.sc.storage.StorageService;
import com.cv.sc.storage.impl.DBStorageServiceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;

public class OrchestratorImpl implements Orchestrator {

    private static OrchestratorImpl instance;
    private StorageService storageService;

    private Searcher gitHubSearcher;

    public static OrchestratorImpl getInstance() {
        if (instance == null) {
            instance = new OrchestratorImpl();
        }
        return instance;
    }
    public OrchestratorImpl() {
        storageService = DBStorageServiceImpl.getInstance();
        gitHubSearcher = GitHubSearcher.getInstance();
    }

    @Override
    public SearchResponse search(Config config) throws HttpClientException, IOException {

        SearchResponse searchResponse = new SearchResponse();

        if (Objects.nonNull(config.getUserSearchKeywords())) {
            for (String searchTerm : config.getUserSearchKeywords()) {
                searchResponse.addUserSearchResult(Map.of(searchTerm, gitHubSearcher.getUserSearchResult(searchTerm)));
            }
        }

        if (Objects.nonNull(config.getCodeSearchKeywords())) {
            for (String searchTerm : config.getCodeSearchKeywords()) {
                searchResponse.addContentSearch(Map.of(searchTerm, gitHubSearcher.getContentSearchResult(searchTerm)));
            }
        }

        if(Objects.nonNull(config.getFileNames())) {
            for (String searchTerm : config.getFileNames()) {
                searchResponse.addFileSearchResult(Map.of(searchTerm, gitHubSearcher.getFileSearchResult(searchTerm)));
            }
        }

        searchResponse = saveSearchResult(searchResponse);
        return searchResponse;
    }

    @Override
    public SearchResponse saveSearchResult(SearchResponse searchResponse) throws UnsupportedEncodingException {
        return (SearchResponse) storageService.save(searchResponse);
    }

}

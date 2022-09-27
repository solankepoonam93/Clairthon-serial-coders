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

public class OrchestratorImpl implements Orchestrator{

    private StorageService storageService;

    private Searcher gitHubSearcher;

    public OrchestratorImpl() {
        storageService = new DBStorageServiceImpl();
        gitHubSearcher = GitHubSearcher.getInstance();
    }

    @Override
    public SearchResponse search(Config config) throws HttpClientException, IOException {

        SearchResponse searchResponse = new SearchResponse();

        for(String searchTerm: config.getUserSearchKeywords()) {
            searchResponse.addUserSearchResult(Map.of(searchTerm, gitHubSearcher.getUserSearchResult(searchTerm)));
        }

        for(String searchTerm: config.getCodeSearchKeywords()) {
            searchResponse.addContentSearch(Map.of(searchTerm, gitHubSearcher.getContentSearchResult(searchTerm)));
        }

        for(String searchTerm: config.getFileNames()) {
            searchResponse.addFileSearchResult(Map.of(searchTerm, gitHubSearcher.getFileSearchResult(searchTerm)));
        }

        // TODO Repo Search
        //Repo Search

        searchResponse = saveSearchResult(searchResponse);
        return searchResponse;
    }

    @Override
    public SearchResponse saveSearchResult(SearchResponse searchResponse) throws UnsupportedEncodingException {
        return (SearchResponse) storageService.save(searchResponse);
    }

}

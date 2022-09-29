package com.cv.sc.pipeline.searcher;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GithubUser;

import java.io.IOException;
import java.util.List;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/09/22
 */
public interface Searcher {


    List<GithubUser> getUserSearchResult(String searchTerm) throws HttpClientException, IOException;

    List<GitHubContentSearch> getContentSearchResult(String searchTerm) throws HttpClientException, IOException;

    List<GitHubFileSearch> getFileSearchResult(String searchTerm) throws HttpClientException, IOException;

    SearchResponse search(Config config) throws HttpClientException, IOException;
}

package com.cv.sc.pipeline.searcher;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.SCEntity;
import com.cv.sc.model.github.GitHubEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/09/22
 */
public interface Searcher {


    List<GitHubEntity> getUserSearchResult(String searchTerm) throws HttpClientException, IOException;

    List<GitHubEntity> getContentSearchResult(String searchTerm) throws HttpClientException, IOException;

    List<GitHubEntity> getFileSearchResult(String searchTerm) throws HttpClientException, IOException;

}

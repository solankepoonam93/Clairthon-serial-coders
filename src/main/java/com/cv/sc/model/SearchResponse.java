package com.cv.sc.model;

import com.cv.sc.model.github.GitHubEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class SearchResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SearchResponse() {
        userSearchResults = new ArrayList<>();
        contentSearchResults = new ArrayList<>();
        repoSearchResults = new ArrayList<>();
        fileSearchResults = new ArrayList<>();
    }

    // [
    // userSearchTerm1: result1, result2, result3,
    // userSearchTerm2: result1, result2, result3,
    // userSearchTerm3: result1, result2, result3
    // ]

    @Lob
    private List<Map<String, List<GitHubEntity>>> userSearchResults;

    // [
    // contentSearchTerm1: result1, result2, result3,
    // contentSearchTerm2: result1, result2, result3,
    // contentSearchTerm3: result1, result2, result3
    // ]
    @Lob
    private List<Map<String, List<GitHubEntity>>> contentSearchResults;

    // [
    // repoSearchTerm1: result1, result2, result3,
    // repoSearchTerm2: result1, result2, result3,
    // repoSearchTerm3: result1, result2, result3
    // ]

    public void addUserSearchResult(Map<String, List<GitHubEntity>> userResult) {
        userSearchResults.add(userResult);
    }

    public void addContentSearch(Map<String, List<GitHubEntity>> contentResult) {
        contentSearchResults.add(contentResult);
    }

    public void addRepoSearchResult(Map<String, List<GitHubEntity>> repoResult) {
        repoSearchResults.add(repoResult);
    }

    public void addFileSearchResult(Map<String, List<GitHubEntity>> fileResult) {
        fileSearchResults.add(fileResult);
    }

    @Lob
    private List<Map<String, List<GitHubEntity>>> repoSearchResults;

    @Lob
    private List<Map<String, List<GitHubEntity>>> fileSearchResults;


    public List<Map<String, List<GitHubEntity>>> getUserSearchResults() {
        return userSearchResults;
    }

    public void setUserSearchResults(List<Map<String, List<GitHubEntity>>> userSearchResults) {
        this.userSearchResults = userSearchResults;
    }

    public List<Map<String, List<GitHubEntity>>> getContentSearchResults() {
        return contentSearchResults;
    }

    public void setContentSearchResults(List<Map<String, List<GitHubEntity>>> contentSearchResults) {
        this.contentSearchResults = contentSearchResults;
    }

    public List<Map<String, List<GitHubEntity>>> getRepoSearchResults() {
        return repoSearchResults;
    }

    public void setRepoSearchResults(List<Map<String, List<GitHubEntity>>> repoSearchResults) {
        this.repoSearchResults = repoSearchResults;
    }

    public List<Map<String, List<GitHubEntity>>> getFileSearchResults() {
        return fileSearchResults;
    }

    public void setFileSearchResults(List<Map<String, List<GitHubEntity>>> fileSearchResults) {
        this.fileSearchResults = fileSearchResults;
    }
}

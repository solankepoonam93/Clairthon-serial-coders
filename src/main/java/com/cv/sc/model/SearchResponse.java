package com.cv.sc.model;

import com.cv.sc.model.github.GitHubEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class SearchResponse implements SCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String userSearchJsonResultString;

    @Lob
    private String contentSearchJsonResultString;

    @Lob
    private String fileSearchJsonResultString;

    @Transient
    private ObjectMapper objectMapper;

    @Transient
    private List<Map<String, List<GitHubEntity>>> userSearchResults;
    @Transient
    private List<Map<String, List<GitHubEntity>>> contentSearchResults;
    @Transient
    private List<Map<String, List<GitHubEntity>>> fileSearchResults;

    public SearchResponse() {
        userSearchResults = new ArrayList<>();
        contentSearchResults = new ArrayList<>();
        fileSearchResults = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userSearchResults);
    }

    public void setUserSearchJsonResultString(String userSearchJsonResultString) {
        this.userSearchJsonResultString = userSearchJsonResultString;
    }

    public String getContentSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(contentSearchResults);
    }

    public void setContentSearchJsonResultString(String contentSearchJsonResultString) {
        this.contentSearchJsonResultString = contentSearchJsonResultString;
    }

    public String getFileSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(fileSearchResults);
    }

    public void setFileSearchJsonResultString(String fileSearchJsonResultString) {
        this.fileSearchJsonResultString = fileSearchJsonResultString;
    }

    public void addUserSearchResult(Map<String, List<GitHubEntity>> userResult) {
        userSearchResults.add(userResult);
    }

    public void addContentSearch(Map<String, List<GitHubEntity>> contentResult) {
        contentSearchResults.add(contentResult);
    }

    public void addFileSearchResult(Map<String, List<GitHubEntity>> fileResult) {
        fileSearchResults.add(fileResult);
    }

    public List<Map<String, List<GitHubEntity>>> getUserSearchResults() {
        return userSearchResults;
    }

    public List<Map<String, List<GitHubEntity>>> getContentSearchResults() {
        return contentSearchResults;
    }

    public List<Map<String, List<GitHubEntity>>> getFileSearchResults() {
        return fileSearchResults;
    }
}

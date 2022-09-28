package com.cv.sc.model;

import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubEntity;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GithubUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Access(AccessType.PROPERTY)
public class SearchResponse implements SCEntity {

    private Long id;

    private String userSearchJsonResultString;

    private String contentSearchJsonResultString;

    private String fileSearchJsonResultString;

    @Transient
    private ObjectMapper objectMapper;
    @Transient
    private List<Map<String, List<GithubUser>>> userSearchResults;
    @Transient
    private List<Map<String, List<GitHubContentSearch>>> contentSearchResults;
    @Transient
    private List<Map<String, List<GitHubFileSearch>>> fileSearchResults;

    public SearchResponse() {
        userSearchResults = new ArrayList<>();
        contentSearchResults = new ArrayList<>();
        fileSearchResults = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    public String getUserSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userSearchResults);
    }

    public void setUserSearchJsonResultString(String userSearchJsonResultString) {
        this.userSearchJsonResultString = userSearchJsonResultString;
    }

    @Lob
    public String getContentSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(contentSearchResults);
    }

    public void setContentSearchJsonResultString(String contentSearchJsonResultString) {
        this.contentSearchJsonResultString = contentSearchJsonResultString;
    }

    @Lob
    public String getFileSearchJsonResultString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(fileSearchResults);
    }

    public void setFileSearchJsonResultString(String fileSearchJsonResultString) {
        this.fileSearchJsonResultString = fileSearchJsonResultString;
    }

    public void addUserSearchResult(Map<String, List<GithubUser>> userResult) {
        userSearchResults.add(userResult);
    }

    public void addContentSearch(Map<String, List<GitHubContentSearch>> contentResult) {
        contentSearchResults.add(contentResult);
    }

    public void addFileSearchResult(Map<String, List<GitHubFileSearch>> fileResult) {
        fileSearchResults.add(fileResult);
    }

    @Transient
    public List<Map<String, List<GithubUser>>> getUserSearchResults() {
        return userSearchResults;
    }

    @Transient
    public List<Map<String, List<GitHubContentSearch>>> getContentSearchResults() {
        return contentSearchResults;
    }

    @Transient
    public List<Map<String, List<GitHubFileSearch>>> getFileSearchResults() {
        return fileSearchResults;
    }

    @Transient
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @JsonIgnore
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setUserSearchResults(List<Map<String, List<GithubUser>>> userSearchResults) {
        this.userSearchResults = userSearchResults;
    }

    public void setContentSearchResults(List<Map<String, List<GitHubContentSearch>>> contentSearchResults) {
        this.contentSearchResults = contentSearchResults;
    }

    public void setFileSearchResults(List<Map<String, List<GitHubFileSearch>>> fileSearchResults) {
        this.fileSearchResults = fileSearchResults;
    }
}

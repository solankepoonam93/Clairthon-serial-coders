package com.cv.sc.model.github;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 22/09/22
 */
// We are considering only few of the returned fields for now. Please check at the bottom to see which all fields are returned.
public class GitHubContentSearch implements GitHubEntity {
    private String name;
    private String url;
    private GitHubRepository repository;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GitHubRepository getRepository() {
        return repository;
    }

    public void setRepository(GitHubRepository repository) {
        this.repository = repository;
    }
}


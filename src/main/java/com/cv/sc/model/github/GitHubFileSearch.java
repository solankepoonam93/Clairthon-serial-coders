package com.cv.sc.model.github;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 26/09/22
 */
public class GitHubFileSearch implements GitHubEntity {
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

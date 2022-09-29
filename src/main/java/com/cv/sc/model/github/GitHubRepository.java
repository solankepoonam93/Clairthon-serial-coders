package com.cv.sc.model.github;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 22/09/22
 */
// We are considering only few of the returned fields for now. Please check at the bottom to see which all fields are returned.
public class GitHubRepository implements GitHubEntity {
    private String id;
    private String name;
    private String fullName;

    @JsonAlias("private")
    private Boolean accessible;
    private GithubUser owner;
    private String url;
    private Boolean fork;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public GithubUser getOwner() {
        return owner;
    }

    public void setOwner(GithubUser owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }
}

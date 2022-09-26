package com.cv.sc.model.github;

/**
 * Represents a GitHub User.<p>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/09/22
 */
// We are considering only few of the returned fields for now. Please check at the bottom to see which all fields are returned.

public class GithubUser implements GitHubEntity {
    private Long id;
    private String login;
    private String type;
    private Float score;
    private String repos_url;
    private String avatar_url;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
/*
{
            "login": "solankepoonam",
            "id": 99260438,
            "node_id": "U_kgDOBeqYFg",
            "avatar_url": "https://avatars.githubusercontent.com/u/99260438?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/solankepoonam",
            "html_url": "https://github.com/solankepoonam",
            "followers_url": "https://api.github.com/users/solankepoonam/followers",
            "following_url": "https://api.github.com/users/solankepoonam/following{/other_user}",
            "gists_url": "https://api.github.com/users/solankepoonam/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/solankepoonam/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/solankepoonam/subscriptions",
            "organizations_url": "https://api.github.com/users/solankepoonam/orgs",
            "repos_url": "https://api.github.com/users/solankepoonam/repos",
            "events_url": "https://api.github.com/users/solankepoonam/events{/privacy}",
            "received_events_url": "https://api.github.com/users/solankepoonam/received_events",
            "type": "User",
            "site_admin": false,
            "score": 1.0
        }
 */
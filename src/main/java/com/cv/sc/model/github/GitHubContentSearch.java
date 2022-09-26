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

/*
{
      "name": "GitHub.javap",
      "path": ".jar/jar11/assemble/slang-braindump/assemble/content/org/kohsuke/github/GitHub.javap",
      "sha": "8f403f0baa1a13ddf8eb62424b4e30b2fd79d437",
      "url": "https://api.github.com/repositories/497735548/contents/.jar/jar11/assemble/slang-braindump/assemble/content/org/kohsuke/github/GitHub.javap?ref=875c2c2a79eff1a8f34e672ca5d7998ef6249adf",
      "git_url": "https://api.github.com/repositories/497735548/git/blobs/8f403f0baa1a13ddf8eb62424b4e30b2fd79d437",
      "html_url": "https://github.com/matthewweis/slang-braindump/blob/875c2c2a79eff1a8f34e672ca5d7998ef6249adf/.jar/jar11/assemble/slang-braindump/assemble/content/org/kohsuke/github/GitHub.javap",
      "repository": {
        "id": 497735548,
        "node_id": "R_kgDOHarXfA",
        "name": "slang-braindump",
        "full_name": "matthewweis/slang-braindump",
        "private": false,
        "owner": {
          "login": "matthewweis",
          "id": 4490060,
          "node_id": "MDQ6VXNlcjQ0OTAwNjA=",
          "avatar_url": "https://avatars.githubusercontent.com/u/4490060?v=4",
          "gravatar_id": "",
          "url": "https://api.github.com/users/matthewweis",
          "html_url": "https://github.com/matthewweis",
          "followers_url": "https://api.github.com/users/matthewweis/followers",
          "following_url": "https://api.github.com/users/matthewweis/following{/other_user}",
          "gists_url": "https://api.github.com/users/matthewweis/gists{/gist_id}",
          "starred_url": "https://api.github.com/users/matthewweis/starred{/owner}{/repo}",
          "subscriptions_url": "https://api.github.com/users/matthewweis/subscriptions",
          "organizations_url": "https://api.github.com/users/matthewweis/orgs",
          "repos_url": "https://api.github.com/users/matthewweis/repos",
          "events_url": "https://api.github.com/users/matthewweis/events{/privacy}",
          "received_events_url": "https://api.github.com/users/matthewweis/received_events",
          "type": "User",
          "site_admin": false
        },
        "html_url": "https://github.com/matthewweis/slang-braindump",
        "description": null,
        "fork": false,
        "url": "https://api.github.com/repos/matthewweis/slang-braindump",
        "forks_url": "https://api.github.com/repos/matthewweis/slang-braindump/forks",
        "keys_url": "https://api.github.com/repos/matthewweis/slang-braindump/keys{/key_id}",
        "collaborators_url": "https://api.github.com/repos/matthewweis/slang-braindump/collaborators{/collaborator}",
        "teams_url": "https://api.github.com/repos/matthewweis/slang-braindump/teams",
        "hooks_url": "https://api.github.com/repos/matthewweis/slang-braindump/hooks",
        "issue_events_url": "https://api.github.com/repos/matthewweis/slang-braindump/issues/events{/number}",
        "events_url": "https://api.github.com/repos/matthewweis/slang-braindump/events",
        "assignees_url": "https://api.github.com/repos/matthewweis/slang-braindump/assignees{/user}",
        "branches_url": "https://api.github.com/repos/matthewweis/slang-braindump/branches{/branch}",
        "tags_url": "https://api.github.com/repos/matthewweis/slang-braindump/tags",
        "blobs_url": "https://api.github.com/repos/matthewweis/slang-braindump/git/blobs{/sha}",
        "git_tags_url": "https://api.github.com/repos/matthewweis/slang-braindump/git/tags{/sha}",
        "git_refs_url": "https://api.github.com/repos/matthewweis/slang-braindump/git/refs{/sha}",
        "trees_url": "https://api.github.com/repos/matthewweis/slang-braindump/git/trees{/sha}",
        "statuses_url": "https://api.github.com/repos/matthewweis/slang-braindump/statuses/{sha}",
        "languages_url": "https://api.github.com/repos/matthewweis/slang-braindump/languages",
        "stargazers_url": "https://api.github.com/repos/matthewweis/slang-braindump/stargazers",
        "contributors_url": "https://api.github.com/repos/matthewweis/slang-braindump/contributors",
        "subscribers_url": "https://api.github.com/repos/matthewweis/slang-braindump/subscribers",
        "subscription_url": "https://api.github.com/repos/matthewweis/slang-braindump/subscription",
        "commits_url": "https://api.github.com/repos/matthewweis/slang-braindump/commits{/sha}",
        "git_commits_url": "https://api.github.com/repos/matthewweis/slang-braindump/git/commits{/sha}",
        "comments_url": "https://api.github.com/repos/matthewweis/slang-braindump/comments{/number}",
        "issue_comment_url": "https://api.github.com/repos/matthewweis/slang-braindump/issues/comments{/number}",
        "contents_url": "https://api.github.com/repos/matthewweis/slang-braindump/contents/{+path}",
        "compare_url": "https://api.github.com/repos/matthewweis/slang-braindump/compare/{base}...{head}",
        "merges_url": "https://api.github.com/repos/matthewweis/slang-braindump/merges",
        "archive_url": "https://api.github.com/repos/matthewweis/slang-braindump/{archive_format}{/ref}",
        "downloads_url": "https://api.github.com/repos/matthewweis/slang-braindump/downloads",
        "issues_url": "https://api.github.com/repos/matthewweis/slang-braindump/issues{/number}",
        "pulls_url": "https://api.github.com/repos/matthewweis/slang-braindump/pulls{/number}",
        "milestones_url": "https://api.github.com/repos/matthewweis/slang-braindump/milestones{/number}",
        "notifications_url": "https://api.github.com/repos/matthewweis/slang-braindump/notifications{?since,all,participating}",
        "labels_url": "https://api.github.com/repos/matthewweis/slang-braindump/labels{/name}",
        "releases_url": "https://api.github.com/repos/matthewweis/slang-braindump/releases{/id}",
        "deployments_url": "https://api.github.com/repos/matthewweis/slang-braindump/deployments"
      },
      "score": 1
    }
 */

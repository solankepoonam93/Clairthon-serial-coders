package com.cv.sc.storage;

import com.cv.sc.model.Config;
import com.cv.sc.model.SearchParent;
import com.cv.sc.model.SearchResult;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class StorageTest {
    private StorageService dbStorageService = new DBStorageServiceImpl();


    public static final String SAMPLE_RESULT = "{\n" +
            "    \"total_count\": 1,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"login\": \"solankepoonam\",\n" +
            "            \"id\": 99260438,\n" +
            "            \"node_id\": \"U_kgDOBeqYFg\",\n" +
            "            \"avatar_url\": \"https://avatars.githubusercontent.com/u/99260438?v=4\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void testDBStorage() throws UnsupportedEncodingException {
        // Save config in db
        Config config = getConfig();
        dbStorageService.save(config);
        Assert.assertNotNull(config.getId());
        // save search parent in db
        SearchParent searchParent = getSearchParent(config);
        dbStorageService.save(searchParent);
        Assert.assertNotNull(searchParent.getId());

        SearchResult searchResult = getSearchResult(searchParent);
        dbStorageService.save(searchResult);
        Assert.assertNotNull(searchResult.getId());
    }

    @Test
    public void updateTest() throws IOException {
        // Save config in db
        Config config = getConfig();
        dbStorageService.save(config);
        // save search parent in db
        SearchParent searchParent = getSearchParent(config);
        dbStorageService.save(searchParent);

        SearchResult searchResult = getSearchResult(searchParent);
        searchResult = (SearchResult) dbStorageService.save(searchResult);

        searchResult.setQueryUrl("http://test_updated_query_url");
        searchResult.setJsonResult("Updated Result inside TEST");
        SearchResult searchResultUpdated = (SearchResult) dbStorageService.update(searchResult);

        Assert.assertTrue(searchResultUpdated.getJsonResult().equals("Updated Result inside TEST"));
        Assert.assertTrue(searchResultUpdated.getQueryUrl().equals("http://test_updated_query_url"));
    }

    private SearchResult getSearchResult(SearchParent searchParent) {
        SearchResult searchResult = new SearchResult();
        searchResult.setQueryUrl("https://api.github.com/search/users?q=solankepoonam in:user");
        searchResult.setSearchParent(searchParent);
        searchResult.setJsonResult("{\\n    \\\"total_count\\\": 2,\\n    \\\"incomplete_results\\\": false,\\n    \\\"items\\\": [\\n        {\\n            \\\"name\\\": \\\"ChartController.php\\\",\\n            \\\"path\\\": \\\"app/Http/Controllers/ChartController.php\\\",\\n            \\\"sha\\\": \\\"612ec7fdecf862c7836bc01eea52b2daf5d388ae\\\",\\n            \\\"url\\\": \\\"https://api.github.com/repositories/215595925/contents/app/Http/Controllers/ChartController.php?ref=f227fe038908ab8cd417143271dcd77bd0abf205\\\",\\n            \\\"git_url\\\": \\\"https://api.github.com/repositories/215595925/git/blobs/612ec7fdecf862c7836bc01eea52b2daf5d388ae\\\",\\n            \\\"html_url\\\": \\\"https://github.com/barqaab-sohail/hrms/blob/f227fe038908ab8cd417143271dcd77bd0abf205/app/Http/Controllers/ChartController.php\\\",\\n            \\\"repository\\\": {\\n                \\\"id\\\": 215595925,\\n                \\\"node_id\\\": \\\"MDEwOlJlcG9zaXRvcnkyMTU1OTU5MjU=\\\",\\n                \\\"name\\\": \\\"hrms\\\",\\n                \\\"full_name\\\": \\\"barqaab-sohail/hrms\\\",\\n                \\\"private\\\": false,\\n                \\\"owner\\\": {\\n                    \\\"login\\\": \\\"barqaab-sohail\\\",\\n                    \\\"id\\\": 47312385,\\n                    \\\"node_id\\\": \\\"MDQ6VXNlcjQ3MzEyMzg1\\\",\\n                    \\\"avatar_url\\\": \\\"https://avatars.githubusercontent.com/u/47312385?v=4\\\",\\n                    \\\"gravatar_id\\\": \\\"\\\",\\n                    \\\"url\\\": \\\"https://api.github.com/users/barqaab-sohail\\\",\\n                    \\\"html_url\\\": \\\"https://github.com/barqaab-sohail\\\",\\n                    \\\"followers_url\\\": \\\"https://api.github.com/users/barqaab-sohail/followers\\\",\\n                    \\\"following_url\\\": \\\"https://api.github.com/users/barqaab-sohail/following{/other_user}\\\",\\n                    \\\"gists_url\\\": \\\"https://api.github.com/users/barqaab-sohail/gists{/gist_id}\\\",\\n                    \\\"starred_url\\\": \\\"https://api.github.com/users/barqaab-sohail/starred{/owner}{/repo}\\\",\\n                    \\\"subscriptions_url\\\": \\\"https://api.github.com/users/barqaab-sohail/subscriptions\\\",\\n                    \\\"organizations_url\\\": \\\"https://api.github.com/users/barqaab-sohail/orgs\\\",\\n                    \\\"repos_url\\\": \\\"https://api.github.com/users/barqaab-sohail/repos\\\",\\n                    \\\"events_url\\\": \\\"https://api.github.com/users/barqaab-sohail/events{/privacy}\\\",\\n                    \\\"received_events_url\\\": \\\"https://api.github.com/users/barqaab-sohail/received_events\\\",\\n                    \\\"type\\\": \\\"User\\\",\\n                    \\\"site_admin\\\": false\\n                },\\n                \\\"html_url\\\": \\\"https://github.com/barqaab-sohail/hrms\\\",\\n                \\\"description\\\": null,\\n                \\\"fork\\\": false,\\n                \\\"url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms\\\",\\n                \\\"forks_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/forks\\\",\\n                \\\"keys_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/keys{/key_id}\\\",\\n                \\\"collaborators_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/collaborators{/collaborator}\\\",\\n                \\\"teams_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/teams\\\",\\n                \\\"hooks_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/hooks\\\",\\n                \\\"issue_events_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/issues/events{/number}\\\",\\n                \\\"events_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/events\\\",\\n                \\\"assignees_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/assignees{/user}\\\",\\n                \\\"branches_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/branches{/branch}\\\",\\n                \\\"tags_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/tags\\\",\\n                \\\"blobs_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/git/blobs{/sha}\\\",\\n                \\\"git_tags_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/git/tags{/sha}\\\",\\n                \\\"git_refs_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/git/refs{/sha}\\\",\\n                \\\"trees_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/git/trees{/sha}\\\",\\n                \\\"statuses_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/statuses/{sha}\\\",\\n                \\\"languages_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/languages\\\",\\n                \\\"stargazers_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/stargazers\\\",\\n                \\\"contributors_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/contributors\\\",\\n                \\\"subscribers_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/subscribers\\\",\\n                \\\"subscription_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/subscription\\\",\\n                \\\"commits_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/commits{/sha}\\\",\\n                \\\"git_commits_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/git/commits{/sha}\\\",\\n                \\\"comments_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/comments{/number}\\\",\\n                \\\"issue_comment_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/issues/comments{/number}\\\",\\n                \\\"contents_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/contents/{+path}\\\",\\n                \\\"compare_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/compare/{base}...{head}\\\",\\n                \\\"merges_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/merges\\\",\\n                \\\"archive_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/{archive_format}{/ref}\\\",\\n                \\\"downloads_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/downloads\\\",\\n                \\\"issues_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/issues{/number}\\\",\\n                \\\"pulls_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/pulls{/number}\\\",\\n                \\\"milestones_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/milestones{/number}\\\",\\n                \\\"notifications_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/notifications{?since,all,participating}\\\",\\n                \\\"labels_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/labels{/name}\\\",\\n                \\\"releases_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/releases{/id}\\\",\\n                \\\"deployments_url\\\": \\\"https://api.github.com/repos/barqaab-sohail/hrms/deployments\\\"\\n            },\\n            \\\"score\\\": 1.0\\n        },\\n        {\\n            \\\"name\\\": \\\"CountAboveSixty.java\\\",\\n            \\\"path\\\": \\\"CountAboveSixty.java\\\",\\n            \\\"sha\\\": \\\"b6663dd85eaf94f7033059c2fc393d43eea978b3\\\",\\n            \\\"url\\\": \\\"https://api.github.com/repositories/412374660/contents/CountAboveSixty.java?ref=2ff2016852ab4b4e831400687f3e6bedfdce31c8\\\",\\n            \\\"git_url\\\": \\\"https://api.github.com/repositories/412374660/git/blobs/b6663dd85eaf94f7033059c2fc393d43eea978b3\\\",\\n            \\\"html_url\\\": \\\"https://github.com/AmrutaChichani/Java_8_demo_project/blob/2ff2016852ab4b4e831400687f3e6bedfdce31c8/CountAboveSixty.java\\\",\\n            \\\"repository\\\": {\\n                \\\"id\\\": 412374660,\\n                \\\"node_id\\\": \\\"R_kgDOGJRWhA\\\",\\n                \\\"name\\\": \\\"Java_8_demo_project\\\",\\n                \\\"full_name\\\": \\\"AmrutaChichani/Java_8_demo_project\\\",\\n                \\\"private\\\": false,\\n                \\\"owner\\\": {\\n                    \\\"login\\\": \\\"AmrutaChichani\\\",\\n                    \\\"id\\\": 91716499,\\n                    \\\"node_id\\\": \\\"U_kgDOBXd7kw\\\",\\n                    \\\"avatar_url\\\": \\\"https://avatars.githubusercontent.com/u/91716499?v=4\\\",\\n                    \\\"gravatar_id\\\": \\\"\\\",\\n                    \\\"url\\\": \\\"https://api.github.com/users/AmrutaChichani\\\",\\n                    \\\"html_url\\\": \\\"https://github.com/AmrutaChichani\\\",\\n                    \\\"followers_url\\\": \\\"https://api.github.com/users/AmrutaChichani/followers\\\",\\n                    \\\"following_url\\\": \\\"https://api.github.com/users/AmrutaChichani/following{/other_user}\\\",\\n                    \\\"gists_url\\\": \\\"https://api.github.com/users/AmrutaChichani/gists{/gist_id}\\\",\\n                    \\\"starred_url\\\": \\\"https://api.github.com/users/AmrutaChichani/starred{/owner}{/repo}\\\",\\n                    \\\"subscriptions_url\\\": \\\"https://api.github.com/users/AmrutaChichani/subscriptions\\\",\\n                    \\\"organizations_url\\\": \\\"https://api.github.com/users/AmrutaChichani/orgs\\\",\\n                    \\\"repos_url\\\": \\\"https://api.github.com/users/AmrutaChichani/repos\\\",\\n                    \\\"events_url\\\": \\\"https://api.github.com/users/AmrutaChichani/events{/privacy}\\\",\\n                    \\\"received_events_url\\\": \\\"https://api.github.com/users/AmrutaChichani/received_events\\\",\\n                    \\\"type\\\": \\\"User\\\",\\n                    \\\"site_admin\\\": false\\n                },\\n                \\\"html_url\\\": \\\"https://github.com/AmrutaChichani/Java_8_demo_project\\\",\\n                \\\"description\\\": \\\"Demo project repository\\\",\\n                \\\"fork\\\": false,\\n                \\\"url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project\\\",\\n                \\\"forks_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/forks\\\",\\n                \\\"keys_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/keys{/key_id}\\\",\\n                \\\"collaborators_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/collaborators{/collaborator}\\\",\\n                \\\"teams_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/teams\\\",\\n                \\\"hooks_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/hooks\\\",\\n                \\\"issue_events_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/issues/events{/number}\\\",\\n                \\\"events_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/events\\\",\\n                \\\"assignees_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/assignees{/user}\\\",\\n                \\\"branches_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/branches{/branch}\\\",\\n                \\\"tags_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/tags\\\",\\n                \\\"blobs_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/git/blobs{/sha}\\\",\\n                \\\"git_tags_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/git/tags{/sha}\\\",\\n                \\\"git_refs_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/git/refs{/sha}\\\",\\n                \\\"trees_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/git/trees{/sha}\\\",\\n                \\\"statuses_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/statuses/{sha}\\\",\\n                \\\"languages_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/languages\\\",\\n                \\\"stargazers_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/stargazers\\\",\\n                \\\"contributors_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/contributors\\\",\\n                \\\"subscribers_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/subscribers\\\",\\n                \\\"subscription_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/subscription\\\",\\n                \\\"commits_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/commits{/sha}\\\",\\n                \\\"git_commits_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/git/commits{/sha}\\\",\\n                \\\"comments_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/comments{/number}\\\",\\n                \\\"issue_comment_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/issues/comments{/number}\\\",\\n                \\\"contents_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/contents/{+path}\\\",\\n                \\\"compare_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/compare/{base}...{head}\\\",\\n                \\\"merges_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/merges\\\",\\n                \\\"archive_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/{archive_format}{/ref}\\\",\\n                \\\"downloads_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/downloads\\\",\\n                \\\"issues_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/issues{/number}\\\",\\n                \\\"pulls_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/pulls{/number}\\\",\\n                \\\"milestones_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/milestones{/number}\\\",\\n                \\\"notifications_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/notifications{?since,all,participating}\\\",\\n                \\\"labels_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/labels{/name}\\\",\\n                \\\"releases_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/releases{/id}\\\",\\n                \\\"deployments_url\\\": \\\"https://api.github.com/repos/AmrutaChichani/Java_8_demo_project/deployments\\\"\\n            },\\n            \\\"score\\\": 1.0\\n        }\\n    ]\\n}");
        return searchResult;
    }

    private static SearchParent getSearchParent(Config config) {
        SearchParent searchParent = new SearchParent();
        searchParent.setStatus("Complete");
        searchParent.setModifiedDate(new Date());
        searchParent.setUser("Bhushan");
        searchParent.setConfig(config);
        return searchParent;
    }

    private static Config getConfig() {
        Config config = new Config();
        config.setModifiedDate(new Date());
        config.setConfigName("TEST_CONFIG");
        config.setUserSearchKeywords(new String[]{"solankepoonam"});
        return config;
    }
}

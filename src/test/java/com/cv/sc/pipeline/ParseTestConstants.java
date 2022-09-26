package com.cv.sc.pipeline;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 26/09/22
 */
public class ParseTestConstants {

    public static final String USER_SEARCH_RESULT_JSON = "{\n" +
            "    \"total_count\": 1,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"login\": \"solankepoonam\",\n" +
            "            \"id\": 99260438,\n" +
            "            \"node_id\": \"U_kgDOBeqYFg\",\n" +
            "            \"avatar_url\": \"https://avatars.githubusercontent.com/u/99260438?v=4\",\n" +
            "            \"gravatar_id\": \"\",\n" +
            "            \"url\": \"https://api.github.com/users/solankepoonam\",\n" +
            "            \"html_url\": \"https://github.com/solankepoonam\",\n" +
            "            \"followers_url\": \"https://api.github.com/users/solankepoonam/followers\",\n" +
            "            \"following_url\": \"https://api.github.com/users/solankepoonam/following{/other_user}\",\n" +
            "            \"gists_url\": \"https://api.github.com/users/solankepoonam/gists{/gist_id}\",\n" +
            "            \"starred_url\": \"https://api.github.com/users/solankepoonam/starred{/owner}{/repo}\",\n" +
            "            \"subscriptions_url\": \"https://api.github.com/users/solankepoonam/subscriptions\",\n" +
            "            \"organizations_url\": \"https://api.github.com/users/solankepoonam/orgs\",\n" +
            "            \"repos_url\": \"https://api.github.com/users/solankepoonam/repos\",\n" +
            "            \"events_url\": \"https://api.github.com/users/solankepoonam/events{/privacy}\",\n" +
            "            \"received_events_url\": \"https://api.github.com/users/solankepoonam/received_events\",\n" +
            "            \"type\": \"User\",\n" +
            "            \"site_admin\": false,\n" +
            "            \"score\": 1.0\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static String CONTENT_SEARCH_RESULT_JSON="{\n" +
            "    \"total_count\": 10,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"name\": \"2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"path\": \"_posts/2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"sha\": \"effbbe648da87c23004abce7587f3768acb61c35\",\n" +
            "            \"url\": \"https://api.github.com/repositories/173346519/contents/_posts/2022-04-20-solving-problems-with-extensions.adoc?ref=1693d968cc71fd507060a781386222857b6c68d4\",\n" +
            "            \"git_url\": \"https://api.github.com/repositories/173346519/git/blobs/effbbe648da87c23004abce7587f3768acb61c35\",\n" +
            "            \"html_url\": \"https://github.com/quarkusio/quarkusio.github.io/blob/1693d968cc71fd507060a781386222857b6c68d4/_posts/2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"repository\": {\n" +
            "                \"id\": 173346519,\n" +
            "                \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNzMzNDY1MTk=\",\n" +
            "                \"name\": \"quarkusio.github.io\",\n" +
            "                \"full_name\": \"quarkusio/quarkusio.github.io\",\n" +
            "                \"private\": false,\n" +
            "                \"owner\": {\n" +
            "                    \"login\": \"quarkusio\",\n" +
            "                    \"id\": 47638783,\n" +
            "                    \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjQ3NjM4Nzgz\",\n" +
            "                    \"avatar_url\": \"https://avatars.githubusercontent.com/u/47638783?v=4\",\n" +
            "                    \"gravatar_id\": \"\",\n" +
            "                    \"url\": \"https://api.github.com/users/quarkusio\",\n" +
            "                    \"html_url\": \"https://github.com/quarkusio\",\n" +
            "                    \"followers_url\": \"https://api.github.com/users/quarkusio/followers\",\n" +
            "                    \"following_url\": \"https://api.github.com/users/quarkusio/following{/other_user}\",\n" +
            "                    \"gists_url\": \"https://api.github.com/users/quarkusio/gists{/gist_id}\",\n" +
            "                    \"starred_url\": \"https://api.github.com/users/quarkusio/starred{/owner}{/repo}\",\n" +
            "                    \"subscriptions_url\": \"https://api.github.com/users/quarkusio/subscriptions\",\n" +
            "                    \"organizations_url\": \"https://api.github.com/users/quarkusio/orgs\",\n" +
            "                    \"repos_url\": \"https://api.github.com/users/quarkusio/repos\",\n" +
            "                    \"events_url\": \"https://api.github.com/users/quarkusio/events{/privacy}\",\n" +
            "                    \"received_events_url\": \"https://api.github.com/users/quarkusio/received_events\",\n" +
            "                    \"type\": \"Organization\",\n" +
            "                    \"site_admin\": false\n" +
            "                },\n" +
            "                \"html_url\": \"https://github.com/quarkusio/quarkusio.github.io\",\n" +
            "                \"description\": \"Website for Quarkus project\",\n" +
            "                \"fork\": false,\n" +
            "                \"url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io\",\n" +
            "                \"forks_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/forks\",\n" +
            "                \"keys_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/keys{/key_id}\",\n" +
            "                \"collaborators_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/collaborators{/collaborator}\",\n" +
            "                \"teams_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/teams\",\n" +
            "                \"hooks_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/hooks\",\n" +
            "                \"issue_events_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/issues/events{/number}\",\n" +
            "                \"events_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/events\",\n" +
            "                \"assignees_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/assignees{/user}\",\n" +
            "                \"branches_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/branches{/branch}\",\n" +
            "                \"tags_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/tags\",\n" +
            "                \"blobs_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/git/blobs{/sha}\",\n" +
            "                \"git_tags_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/git/tags{/sha}\",\n" +
            "                \"git_refs_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/git/refs{/sha}\",\n" +
            "                \"trees_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/git/trees{/sha}\",\n" +
            "                \"statuses_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/statuses/{sha}\",\n" +
            "                \"languages_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/languages\",\n" +
            "                \"stargazers_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/stargazers\",\n" +
            "                \"contributors_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/contributors\",\n" +
            "                \"subscribers_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/subscribers\",\n" +
            "                \"subscription_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/subscription\",\n" +
            "                \"commits_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/commits{/sha}\",\n" +
            "                \"git_commits_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/git/commits{/sha}\",\n" +
            "                \"comments_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/comments{/number}\",\n" +
            "                \"issue_comment_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/issues/comments{/number}\",\n" +
            "                \"contents_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/contents/{+path}\",\n" +
            "                \"compare_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/compare/{base}...{head}\",\n" +
            "                \"merges_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/merges\",\n" +
            "                \"archive_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/{archive_format}{/ref}\",\n" +
            "                \"downloads_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/downloads\",\n" +
            "                \"issues_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/issues{/number}\",\n" +
            "                \"pulls_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/pulls{/number}\",\n" +
            "                \"milestones_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/milestones{/number}\",\n" +
            "                \"notifications_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/notifications{?since,all,participating}\",\n" +
            "                \"labels_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/labels{/name}\",\n" +
            "                \"releases_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/releases{/id}\",\n" +
            "                \"deployments_url\": \"https://api.github.com/repos/quarkusio/quarkusio.github.io/deployments\"\n" +
            "            },\n" +
            "            \"score\": 1.0\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"path\": \"_posts/2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"sha\": \"effbbe648da87c23004abce7587f3768acb61c35\",\n" +
            "            \"url\": \"https://api.github.com/repositories/174110199/contents/_posts/2022-04-20-solving-problems-with-extensions.adoc?ref=623775ddd19ee5037288d0ef740119eb7abb574c\",\n" +
            "            \"git_url\": \"https://api.github.com/repositories/174110199/git/blobs/effbbe648da87c23004abce7587f3768acb61c35\",\n" +
            "            \"html_url\": \"https://github.com/johnaohara/quarkusio.github.io/blob/623775ddd19ee5037288d0ef740119eb7abb574c/_posts/2022-04-20-solving-problems-with-extensions.adoc\",\n" +
            "            \"repository\": {\n" +
            "                \"id\": 174110199,\n" +
            "                \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNzQxMTAxOTk=\",\n" +
            "                \"name\": \"quarkusio.github.io\",\n" +
            "                \"full_name\": \"johnaohara/quarkusio.github.io\",\n" +
            "                \"private\": false,\n" +
            "                \"owner\": {\n" +
            "                    \"login\": \"johnaohara\",\n" +
            "                    \"id\": 959822,\n" +
            "                    \"node_id\": \"MDQ6VXNlcjk1OTgyMg==\",\n" +
            "                    \"avatar_url\": \"https://avatars.githubusercontent.com/u/959822?v=4\",\n" +
            "                    \"gravatar_id\": \"\",\n" +
            "                    \"url\": \"https://api.github.com/users/johnaohara\",\n" +
            "                    \"html_url\": \"https://github.com/johnaohara\",\n" +
            "                    \"followers_url\": \"https://api.github.com/users/johnaohara/followers\",\n" +
            "                    \"following_url\": \"https://api.github.com/users/johnaohara/following{/other_user}\",\n" +
            "                    \"gists_url\": \"https://api.github.com/users/johnaohara/gists{/gist_id}\",\n" +
            "                    \"starred_url\": \"https://api.github.com/users/johnaohara/starred{/owner}{/repo}\",\n" +
            "                    \"subscriptions_url\": \"https://api.github.com/users/johnaohara/subscriptions\",\n" +
            "                    \"organizations_url\": \"https://api.github.com/users/johnaohara/orgs\",\n" +
            "                    \"repos_url\": \"https://api.github.com/users/johnaohara/repos\",\n" +
            "                    \"events_url\": \"https://api.github.com/users/johnaohara/events{/privacy}\",\n" +
            "                    \"received_events_url\": \"https://api.github.com/users/johnaohara/received_events\",\n" +
            "                    \"type\": \"User\",\n" +
            "                    \"site_admin\": false\n" +
            "                },\n" +
            "                \"html_url\": \"https://github.com/johnaohara/quarkusio.github.io\",\n" +
            "                \"description\": null,\n" +
            "                \"fork\": false,\n" +
            "                \"url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io\",\n" +
            "                \"forks_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/forks\",\n" +
            "                \"keys_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/keys{/key_id}\",\n" +
            "                \"collaborators_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/collaborators{/collaborator}\",\n" +
            "                \"teams_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/teams\",\n" +
            "                \"hooks_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/hooks\",\n" +
            "                \"issue_events_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/issues/events{/number}\",\n" +
            "                \"events_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/events\",\n" +
            "                \"assignees_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/assignees{/user}\",\n" +
            "                \"branches_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/branches{/branch}\",\n" +
            "                \"tags_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/tags\",\n" +
            "                \"blobs_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/git/blobs{/sha}\",\n" +
            "                \"git_tags_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/git/tags{/sha}\",\n" +
            "                \"git_refs_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/git/refs{/sha}\",\n" +
            "                \"trees_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/git/trees{/sha}\",\n" +
            "                \"statuses_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/statuses/{sha}\",\n" +
            "                \"languages_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/languages\",\n" +
            "                \"stargazers_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/stargazers\",\n" +
            "                \"contributors_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/contributors\",\n" +
            "                \"subscribers_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/subscribers\",\n" +
            "                \"subscription_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/subscription\",\n" +
            "                \"commits_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/commits{/sha}\",\n" +
            "                \"git_commits_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/git/commits{/sha}\",\n" +
            "                \"comments_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/comments{/number}\",\n" +
            "                \"issue_comment_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/issues/comments{/number}\",\n" +
            "                \"contents_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/contents/{+path}\",\n" +
            "                \"compare_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/compare/{base}...{head}\",\n" +
            "                \"merges_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/merges\",\n" +
            "                \"archive_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/{archive_format}{/ref}\",\n" +
            "                \"downloads_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/downloads\",\n" +
            "                \"issues_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/issues{/number}\",\n" +
            "                \"pulls_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/pulls{/number}\",\n" +
            "                \"milestones_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/milestones{/number}\",\n" +
            "                \"notifications_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/notifications{?since,all,participating}\",\n" +
            "                \"labels_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/labels{/name}\",\n" +
            "                \"releases_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/releases{/id}\",\n" +
            "                \"deployments_url\": \"https://api.github.com/repos/johnaohara/quarkusio.github.io/deployments\"\n" +
            "            },\n" +
            "            \"score\": 1.0\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static final String FILE_RESULT_JSON = "{\n" +
            "    \"total_count\": 239245,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"name\": \"proland.workspace\",\n" +
            "            \"path\": \"proland.workspace\",\n" +
            "            \"sha\": \"f3570a2653c58777e176ff7404402381869740e6\",\n" +
            "            \"url\": \"https://api.github.com/repositories/188393704/contents/proland.workspace?ref=bcfe8895cc34bdb6fb5fd2c68c681eb1b85aae42\",\n" +
            "            \"git_url\": \"https://api.github.com/repositories/188393704/git/blobs/f3570a2653c58777e176ff7404402381869740e6\",\n" +
            "            \"html_url\": \"https://github.com/hyzboy/proland/blob/bcfe8895cc34bdb6fb5fd2c68c681eb1b85aae42/proland.workspace\",\n" +
            "            \"repository\": {\n" +
            "                \"id\": 188393704,\n" +
            "                \"node_id\": \"MDEwOlJlcG9zaXRvcnkxODgzOTM3MDQ=\",\n" +
            "                \"name\": \"proland\",\n" +
            "                \"full_name\": \"hyzboy/proland\",\n" +
            "                \"private\": false,\n" +
            "                \"owner\": {\n" +
            "                    \"login\": \"hyzboy\",\n" +
            "                    \"id\": 1788285,\n" +
            "                    \"node_id\": \"MDQ6VXNlcjE3ODgyODU=\",\n" +
            "                    \"avatar_url\": \"https://avatars.githubusercontent.com/u/1788285?v=4\",\n" +
            "                    \"gravatar_id\": \"\",\n" +
            "                    \"url\": \"https://api.github.com/users/hyzboy\",\n" +
            "                    \"html_url\": \"https://github.com/hyzboy\",\n" +
            "                    \"followers_url\": \"https://api.github.com/users/hyzboy/followers\",\n" +
            "                    \"following_url\": \"https://api.github.com/users/hyzboy/following{/other_user}\",\n" +
            "                    \"gists_url\": \"https://api.github.com/users/hyzboy/gists{/gist_id}\",\n" +
            "                    \"starred_url\": \"https://api.github.com/users/hyzboy/starred{/owner}{/repo}\",\n" +
            "                    \"subscriptions_url\": \"https://api.github.com/users/hyzboy/subscriptions\",\n" +
            "                    \"organizations_url\": \"https://api.github.com/users/hyzboy/orgs\",\n" +
            "                    \"repos_url\": \"https://api.github.com/users/hyzboy/repos\",\n" +
            "                    \"events_url\": \"https://api.github.com/users/hyzboy/events{/privacy}\",\n" +
            "                    \"received_events_url\": \"https://api.github.com/users/hyzboy/received_events\",\n" +
            "                    \"type\": \"User\",\n" +
            "                    \"site_admin\": false\n" +
            "                },\n" +
            "                \"html_url\": \"https://github.com/hyzboy/proland\",\n" +
            "                \"description\": null,\n" +
            "                \"fork\": false,\n" +
            "                \"url\": \"https://api.github.com/repos/hyzboy/proland\",\n" +
            "                \"forks_url\": \"https://api.github.com/repos/hyzboy/proland/forks\",\n" +
            "                \"keys_url\": \"https://api.github.com/repos/hyzboy/proland/keys{/key_id}\",\n" +
            "                \"collaborators_url\": \"https://api.github.com/repos/hyzboy/proland/collaborators{/collaborator}\",\n" +
            "                \"teams_url\": \"https://api.github.com/repos/hyzboy/proland/teams\",\n" +
            "                \"hooks_url\": \"https://api.github.com/repos/hyzboy/proland/hooks\",\n" +
            "                \"issue_events_url\": \"https://api.github.com/repos/hyzboy/proland/issues/events{/number}\",\n" +
            "                \"events_url\": \"https://api.github.com/repos/hyzboy/proland/events\",\n" +
            "                \"assignees_url\": \"https://api.github.com/repos/hyzboy/proland/assignees{/user}\",\n" +
            "                \"branches_url\": \"https://api.github.com/repos/hyzboy/proland/branches{/branch}\",\n" +
            "                \"tags_url\": \"https://api.github.com/repos/hyzboy/proland/tags\",\n" +
            "                \"blobs_url\": \"https://api.github.com/repos/hyzboy/proland/git/blobs{/sha}\",\n" +
            "                \"git_tags_url\": \"https://api.github.com/repos/hyzboy/proland/git/tags{/sha}\",\n" +
            "                \"git_refs_url\": \"https://api.github.com/repos/hyzboy/proland/git/refs{/sha}\",\n" +
            "                \"trees_url\": \"https://api.github.com/repos/hyzboy/proland/git/trees{/sha}\",\n" +
            "                \"statuses_url\": \"https://api.github.com/repos/hyzboy/proland/statuses/{sha}\",\n" +
            "                \"languages_url\": \"https://api.github.com/repos/hyzboy/proland/languages\",\n" +
            "                \"stargazers_url\": \"https://api.github.com/repos/hyzboy/proland/stargazers\",\n" +
            "                \"contributors_url\": \"https://api.github.com/repos/hyzboy/proland/contributors\",\n" +
            "                \"subscribers_url\": \"https://api.github.com/repos/hyzboy/proland/subscribers\",\n" +
            "                \"subscription_url\": \"https://api.github.com/repos/hyzboy/proland/subscription\",\n" +
            "                \"commits_url\": \"https://api.github.com/repos/hyzboy/proland/commits{/sha}\",\n" +
            "                \"git_commits_url\": \"https://api.github.com/repos/hyzboy/proland/git/commits{/sha}\",\n" +
            "                \"comments_url\": \"https://api.github.com/repos/hyzboy/proland/comments{/number}\",\n" +
            "                \"issue_comment_url\": \"https://api.github.com/repos/hyzboy/proland/issues/comments{/number}\",\n" +
            "                \"contents_url\": \"https://api.github.com/repos/hyzboy/proland/contents/{+path}\",\n" +
            "                \"compare_url\": \"https://api.github.com/repos/hyzboy/proland/compare/{base}...{head}\",\n" +
            "                \"merges_url\": \"https://api.github.com/repos/hyzboy/proland/merges\",\n" +
            "                \"archive_url\": \"https://api.github.com/repos/hyzboy/proland/{archive_format}{/ref}\",\n" +
            "                \"downloads_url\": \"https://api.github.com/repos/hyzboy/proland/downloads\",\n" +
            "                \"issues_url\": \"https://api.github.com/repos/hyzboy/proland/issues{/number}\",\n" +
            "                \"pulls_url\": \"https://api.github.com/repos/hyzboy/proland/pulls{/number}\",\n" +
            "                \"milestones_url\": \"https://api.github.com/repos/hyzboy/proland/milestones{/number}\",\n" +
            "                \"notifications_url\": \"https://api.github.com/repos/hyzboy/proland/notifications{?since,all,participating}\",\n" +
            "                \"labels_url\": \"https://api.github.com/repos/hyzboy/proland/labels{/name}\",\n" +
            "                \"releases_url\": \"https://api.github.com/repos/hyzboy/proland/releases{/id}\",\n" +
            "                \"deployments_url\": \"https://api.github.com/repos/hyzboy/proland/deployments\"\n" +
            "            },\n" +
            "            \"score\": 1.0\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"proland.workspace\",\n" +
            "            \"path\": \"src/proland.workspace\",\n" +
            "            \"sha\": \"f3570a2653c58777e176ff7404402381869740e6\",\n" +
            "            \"url\": \"https://api.github.com/repositories/10308899/contents/src/proland.workspace?ref=fdff3642e36c56af8ec5f9299166a3d4bf36d33a\",\n" +
            "            \"git_url\": \"https://api.github.com/repositories/10308899/git/blobs/f3570a2653c58777e176ff7404402381869740e6\",\n" +
            "            \"html_url\": \"https://github.com/csbrandt/proland-4.0/blob/fdff3642e36c56af8ec5f9299166a3d4bf36d33a/src/proland.workspace\",\n" +
            "            \"repository\": {\n" +
            "                \"id\": 10308899,\n" +
            "                \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMDMwODg5OQ==\",\n" +
            "                \"name\": \"proland-4.0\",\n" +
            "                \"full_name\": \"csbrandt/proland-4.0\",\n" +
            "                \"private\": false,\n" +
            "                \"owner\": {\n" +
            "                    \"login\": \"csbrandt\",\n" +
            "                    \"id\": 1036634,\n" +
            "                    \"node_id\": \"MDQ6VXNlcjEwMzY2MzQ=\",\n" +
            "                    \"avatar_url\": \"https://avatars.githubusercontent.com/u/1036634?v=4\",\n" +
            "                    \"gravatar_id\": \"\",\n" +
            "                    \"url\": \"https://api.github.com/users/csbrandt\",\n" +
            "                    \"html_url\": \"https://github.com/csbrandt\",\n" +
            "                    \"followers_url\": \"https://api.github.com/users/csbrandt/followers\",\n" +
            "                    \"following_url\": \"https://api.github.com/users/csbrandt/following{/other_user}\",\n" +
            "                    \"gists_url\": \"https://api.github.com/users/csbrandt/gists{/gist_id}\",\n" +
            "                    \"starred_url\": \"https://api.github.com/users/csbrandt/starred{/owner}{/repo}\",\n" +
            "                    \"subscriptions_url\": \"https://api.github.com/users/csbrandt/subscriptions\",\n" +
            "                    \"organizations_url\": \"https://api.github.com/users/csbrandt/orgs\",\n" +
            "                    \"repos_url\": \"https://api.github.com/users/csbrandt/repos\",\n" +
            "                    \"events_url\": \"https://api.github.com/users/csbrandt/events{/privacy}\",\n" +
            "                    \"received_events_url\": \"https://api.github.com/users/csbrandt/received_events\",\n" +
            "                    \"type\": \"User\",\n" +
            "                    \"site_admin\": false\n" +
            "                },\n" +
            "                \"html_url\": \"https://github.com/csbrandt/proland-4.0\",\n" +
            "                \"description\": \"A C++/OpenGL library for the real-time realistic rendering of very large and detailed 3D natural scenes on GPU\",\n" +
            "                \"fork\": false,\n" +
            "                \"url\": \"https://api.github.com/repos/csbrandt/proland-4.0\",\n" +
            "                \"forks_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/forks\",\n" +
            "                \"keys_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/keys{/key_id}\",\n" +
            "                \"collaborators_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/collaborators{/collaborator}\",\n" +
            "                \"teams_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/teams\",\n" +
            "                \"hooks_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/hooks\",\n" +
            "                \"issue_events_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/issues/events{/number}\",\n" +
            "                \"events_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/events\",\n" +
            "                \"assignees_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/assignees{/user}\",\n" +
            "                \"branches_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/branches{/branch}\",\n" +
            "                \"tags_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/tags\",\n" +
            "                \"blobs_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/git/blobs{/sha}\",\n" +
            "                \"git_tags_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/git/tags{/sha}\",\n" +
            "                \"git_refs_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/git/refs{/sha}\",\n" +
            "                \"trees_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/git/trees{/sha}\",\n" +
            "                \"statuses_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/statuses/{sha}\",\n" +
            "                \"languages_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/languages\",\n" +
            "                \"stargazers_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/stargazers\",\n" +
            "                \"contributors_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/contributors\",\n" +
            "                \"subscribers_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/subscribers\",\n" +
            "                \"subscription_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/subscription\",\n" +
            "                \"commits_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/commits{/sha}\",\n" +
            "                \"git_commits_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/git/commits{/sha}\",\n" +
            "                \"comments_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/comments{/number}\",\n" +
            "                \"issue_comment_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/issues/comments{/number}\",\n" +
            "                \"contents_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/contents/{+path}\",\n" +
            "                \"compare_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/compare/{base}...{head}\",\n" +
            "                \"merges_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/merges\",\n" +
            "                \"archive_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/{archive_format}{/ref}\",\n" +
            "                \"downloads_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/downloads\",\n" +
            "                \"issues_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/issues{/number}\",\n" +
            "                \"pulls_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/pulls{/number}\",\n" +
            "                \"milestones_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/milestones{/number}\",\n" +
            "                \"notifications_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/notifications{?since,all,participating}\",\n" +
            "                \"labels_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/labels{/name}\",\n" +
            "                \"releases_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/releases{/id}\",\n" +
            "                \"deployments_url\": \"https://api.github.com/repos/csbrandt/proland-4.0/deployments\"\n" +
            "            },\n" +
            "            \"score\": 1.0\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}

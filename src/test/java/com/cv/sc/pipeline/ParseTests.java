package com.cv.sc.pipeline;

import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubEntity;
import com.cv.sc.model.github.GithubUser;
import com.cv.sc.pipeline.searcher.GitHubSearcher;
import com.cv.sc.pipeline.searcher.Searcher;
import com.cv.sc.util.ObjectMapperProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static com.cv.sc.pipeline.ParseTestConstants.*;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 26/09/22
 */
public class ParseTests {

    public ParseTests() {
        this.searcher = GitHubSearcher.getInstance();
    }

    private GitHubSearcher searcher;

    @Test
    public void testUserDeserialization() throws JsonProcessingException {
        List<GithubUser> extract = searcher.extract(USER_SEARCH_RESULT_JSON, GithubUser.class);
        Assert.assertFalse(extract.isEmpty());
        Assert.assertTrue(((GithubUser)(extract.get(0))).getLogin() != null);
    }


    @Test
    public void testContentDeserialization() throws JsonProcessingException {
        List<GitHubContentSearch> extract = searcher.extract(CONTENT_SEARCH_RESULT_JSON, GitHubContentSearch.class);
        Assert.assertFalse(extract.isEmpty());
        Assert.assertTrue(((GitHubContentSearch)(extract.get(0))).getName() != null);
        Assert.assertTrue(extract.size() == 2);
    }

    @Test
    public void testFileDeserialization() throws JsonProcessingException {
        List<GitHubContentSearch> extract = searcher.extract(FILE_RESULT_JSON, GitHubContentSearch.class);
        Assert.assertFalse(extract.isEmpty());
        Assert.assertTrue(((GitHubContentSearch)(extract.get(0))).getName() != null);
        Assert.assertTrue(extract.size() == 2);
    }
}

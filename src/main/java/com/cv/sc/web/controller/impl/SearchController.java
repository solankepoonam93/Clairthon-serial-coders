package com.cv.sc.web.controller.impl;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.APIResponse;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.*;
import com.cv.sc.pipeline.Orchestrator;
import com.cv.sc.pipeline.OrchestratorImpl;
import com.cv.sc.pipeline.searcher.GitHubSearcher;
import com.cv.sc.pipeline.searcher.Searcher;
import com.cv.sc.storage.StorageService;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.web.controller.SCController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@RestController
@RequestMapping("/search")
public class SearchController implements SCController {
    private StorageService storageService;

    private Orchestrator orchestrator;

    public SearchController() {
        storageService = DBStorageServiceImpl.getInstance();
        orchestrator = OrchestratorImpl.getInstance();
    }

    @RequestMapping(path = "/config/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResponse search(@PathVariable Long id, HttpServletRequest request) throws IOException, HttpClientException {
        // fetch config with this id
        Config config = (Config) storageService.fetch(Config.class, id);
        // pass this config to orchestrator.search
        if(request.getParameter("test") != null && request.getParameter("test").equalsIgnoreCase("true")) {
            return getTestSearchResponse();
        } else {
            return orchestrator.search(config);
        }
    }

    private SearchResponse getTestSearchResponse() {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setId(1L);
        List<GithubUser> userList = new ArrayList<>();
        userList.add(getGithubUser());
        searchResponse.addUserSearchResult(Map.of("users", userList));

        List<GitHubContentSearch> contentList = new ArrayList<>();
        GitHubContentSearch contentSearch = getGitHubContentSearch();
        contentList.add(contentSearch);
        searchResponse.addContentSearch(Map.of("content", contentList));

        List<GitHubFileSearch> fileList = new ArrayList<>();
        fileList.add(getGitHubFileSearch(contentSearch));
        searchResponse.addFileSearchResult(Map.of("files", fileList));
        return searchResponse;
    }

    private GitHubFileSearch getGitHubFileSearch(GitHubContentSearch contentSearch) {
        GitHubFileSearch fileSearch = new GitHubFileSearch();
        fileSearch.setName("Test File Search");
        fileSearch.setUrl("https://github.com/file");
        fileSearch.setRepository(contentSearch.getRepository());
        return fileSearch;
    }

    private GitHubContentSearch getGitHubContentSearch() {
        GitHubContentSearch contentSearch = new GitHubContentSearch();
        contentSearch.setName("Test Content");
        GitHubRepository repository = new GitHubRepository();
        repository.setId("1");
        repository.setName("Test Repo");
        repository.setUrl("https://github.com/teamclairvoyant/clairthon-serial-coders");
        contentSearch.setRepository(repository);
        contentSearch.setUrl("https://github.com/");
        return contentSearch;
    }

    private GithubUser getGithubUser() {
        GithubUser user = new GithubUser();
        user.setLogin("bkpune");
        user.setId(1L);
        user.setType("ADMIN");
        user.setAvatar_url("https://facebook.com/bkpune");
        user.setScore(100F);
        return user;
    }
}

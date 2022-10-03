package com.cv.sc.web.controller.impl;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubContentSearch;
import com.cv.sc.model.github.GitHubFileSearch;
import com.cv.sc.model.github.GitHubRepository;
import com.cv.sc.model.github.GithubUser;
import com.cv.sc.pipeline.Orchestrator;
import com.cv.sc.pipeline.OrchestratorImpl;
import com.cv.sc.storage.StorageService;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.web.controller.SCController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@RestController
@RequestMapping("/search")
public class SearchController implements SCController {
    private final StorageService storageService;

    private final Orchestrator orchestrator;

    public SearchController() {
        storageService = DBStorageServiceImpl.getInstance();
        orchestrator = OrchestratorImpl.getInstance();
    }

    @GetMapping(path = "/getSearchResponse/config/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResponse fetchSearchResponseForConfig(@PathVariable Long id, HttpServletRequest request) throws IOException {
        // fetch config with this id
        Config config = (Config) storageService.fetch(Config.class, id);
        List<SearchResponse> list = storageService.fetchWithPredicate(SearchResponse.class, "config", ""+config.getId());
        if(list != null && !list.isEmpty()) {
            List<SearchResponse> collect = list.stream().filter(o1 -> o1.getConfig().getId().equals(config.getId())).sorted((Comparator.comparing(SearchResponse::getCreatedOn))).collect(Collectors.toList());
            return collect.get(0);
        } else {
            return new SearchResponse();
        }
    }

    @GetMapping(path = "/config/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResponse search(@PathVariable Long id, HttpServletRequest request) throws IOException, HttpClientException {
        // fetch config with this id
        Config config = (Config) storageService.fetch(Config.class, id);
        // pass this config to orchestrator.search
        if(request.getParameter("test") != null && request.getParameter("test").equalsIgnoreCase("true")) {
            return getTestSearchResponse(config);
        } else {
            return orchestrator.search(config);
        }
    }

    private SearchResponse getTestSearchResponse(Config config) throws UnsupportedEncodingException {
        SearchResponse searchResponse = new SearchResponse();
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

        searchResponse.setConfig(config);
        orchestrator.saveSearchResult(searchResponse);
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
        user.setAvatarUrl("https://facebook.com/bkpune");
        user.setScore(100F);
        return user;
    }
}

package com.cv.sc.storage;

import com.cv.sc.models.Config;
import com.cv.sc.models.SearchParent;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.storage.impl.S3StorageServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class StorageTest {

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
    public void testS3storage() throws UnsupportedEncodingException {
        StorageService dbStorageService = new DBStorageServiceImpl();

        // Save config in db
        Config config = getConfig();
        dbStorageService.save(config);
        Assert.assertTrue(config.getId() != null);
        // save search parent in db
        SearchParent searchParent = getSearchParent(config);
        dbStorageService.save(searchParent);
        Assert.assertTrue(searchParent.getId() != null);

        SearchResult searchResult = getSearchResult(searchParent);
        dbStorageService.save(searchResult);
        Assert.assertTrue(searchResult.getId() != null);
    }

    @Test
    public void testS3Storage() throws UnsupportedEncodingException {
        StorageService storageService = new S3StorageServiceImpl();
        StorageService dbStorageService = new DBStorageServiceImpl();

        // Save config in db
        Config config = getConfig();
        dbStorageService.save(config);

        // save search parent in db
        SearchParent searchParent = getSearchParent(config);
        dbStorageService.save(searchParent);

        SearchResult searchResult = getSearchResult(searchParent);
        dbStorageService.save(searchResult);

        searchResult.setJsonResult(SAMPLE_RESULT); // to avoid truncation
        SearchResult saved = (SearchResult) storageService.save(searchResult);

        Assert.assertTrue(saved instanceof SearchResult);
        Assert.assertNotNull(saved.getId());
        Assert.assertNotEquals(saved.getJsonResult(), SAMPLE_RESULT);
        Assert.assertEquals(saved.getQueryUrl(), searchResult.getQueryUrl());
        Assert.assertNotNull(saved.getJsonResult());
    }


    private SearchResult getSearchResult(SearchParent searchParent) {
        SearchResult searchResult = new SearchResult();
        searchResult.setQueryUrl("https://api.github.com/search/users?q=solankepoonam in:user");
        searchResult.setSearchParent(searchParent);

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
        config.setUserSearchKeywords("solankepoonam");
        return config;
    }
}

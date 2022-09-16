package com.cv.sc.storage;

import com.cv.sc.models.Config;
import com.cv.sc.models.SearchParent;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.storage.impl.S3StorageServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class StorageTests {

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
        StorageService dbStorageService = new DBStorageServiceImpl();

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

        searchResult.setS3FileUrl(SAMPLE_RESULT); // to avoid truncation
        SearchResult saved = (SearchResult) storageService.save(searchResult);

        Assert.assertTrue(saved instanceof SearchResult);
        Assert.assertNotNull(saved.getId());
        Assert.assertNotEquals(saved.getS3FileUrl(), SAMPLE_RESULT);
        Assert.assertEquals(saved.getQueryUrl(), searchResult.getQueryUrl());
        Assert.assertNotNull(saved.getS3FileUrl());
    }

    @Test
    public void testS3Fetch() throws IOException {
        String fileName = "Json_Result_28_1663316839640.json";
        StorageService storageService = new S3StorageServiceImpl();
        String fileContent = (String) storageService.fetch(String.class, fileName);
        Assert.assertNotNull(fileContent);
        Assert.assertTrue(fileContent.length() > 200);
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

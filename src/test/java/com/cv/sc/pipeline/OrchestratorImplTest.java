package com.cv.sc.pipeline;

import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.model.github.GitHubEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class OrchestratorImplTest {

    OrchestratorImpl orchestrator = new OrchestratorImpl();
    @Test
    public void searchTest() throws Exception {
        Config config = getConfig();
        SearchResponse searchResponse = orchestrator.search(config);
        Assert.assertTrue(searchResponse.getContentSearchResults().size()>0);
        Assert.assertTrue(searchResponse.getUserSearchResults().size()>0);
        Assert.assertTrue(searchResponse.getFileSearchResults().size()>0);
    }

    private Config getConfig() {
        Config config = new Config();
        config.setId(1L);
        config.setConfigName("test1");
        config.setCodeSearchKeywords(new String[]{"CountAboveSixty"});
        config.setCustomerName("Amruta");
        config.setUserSearchKeywords(new String[]{"AmrutaChichani"});
        config.setFileNames(new String[] {"clairthon-serial-coders"});
        config.setRepositoryNames(new String[]{"AmrutaChichani/Java_8_demo_project"});
        return config;
    }
}

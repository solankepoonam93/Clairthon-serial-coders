package com.cv.sc.pipeline;

import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import org.junit.Assert;
import org.junit.Test;

public class OrchestratorImplTest {

    private Orchestrator orchestrator = new OrchestratorImpl();
    @Test
    public void searchTest() throws Exception {
        Config config = getConfig();
        SearchResponse searchResponse = orchestrator.search(config);
        Assert.assertTrue(searchResponse.getContentSearchResults().size()>0);
        Assert.assertTrue(searchResponse.getUserSearchResults().size()>0);
        Assert.assertTrue(searchResponse.getFileSearchResults().size()>0);
        // System.out.println(new ObjectMapper().writeValueAsString(searchResponse));
    }

    private Config getConfig() {
        Config config = new Config();
        config.setId(1L);
        config.setConfigName("test1");
        config.setCodeSearchKeywords(new String[]{"sortByPercentage"});
        config.setCustomerName("TestCustomer1");
        config.setUserSearchKeywords(new String[]{"bhushank"});
        config.setFileNames(new String[] {"SCApplicationImplTest"});
        config.setRepositoryNames(new String[]{"clairthon-serial-coders"});
        return config;
    }
}

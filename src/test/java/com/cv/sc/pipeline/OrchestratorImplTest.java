package com.cv.sc.pipeline;

import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.util.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class OrchestratorImplTest {

    OrchestratorImpl orchestrator = new OrchestratorImpl();
    @Test
    public void searchTest() throws Exception {
        Config config = getConfig();

        SearchResponse result = orchestrator.search(config);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getResponse().containsKey(Constants.JSON_CODE));
        Assert.assertTrue(result.getResponse().containsKey(Constants.JSON_USER));
        Assert.assertTrue(result.getResponse().containsKey(Constants.JSON_FILE));
      /*  Assert.assertTrue(result.getResponse().containsKey(Constants.JSON_REPO));*/
    }

    private Config getConfig() {
        Config config = new Config();
        config.setId(1L);
        config.setConfigName("test1");
        config.setCodeSearchKeywords(new String[]{"sortByNames"});
        config.setCustomerName("Amruta");
        config.setUserSearchKeywords(new String[]{"AmrutaChichani"});
        config.setRepositoryNames(new String[]{"AmrutaChichani/Java_8_demo_project"});
        return config;
    }
}

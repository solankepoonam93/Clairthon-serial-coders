package com.cv.sc.pipeline;

import com.cv.sc.models.Config;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class OrchestratorImplTest {

    OrchestratorImpl orchestrator = new OrchestratorImpl();
    @Test
    public void searchTest() throws Exception {
        Config config = getConfig();

        Map<String , String> result = orchestrator.search(config);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.containsKey("CodeResult"));
        Assert.assertTrue(result.containsKey("UserResult"));
    }

    private Config getConfig() {
        Config config = new Config();
        config.setId(1L);
        config.setConfigName("test1");
        config.setCodeSearchKeywords("CountAboveSixty");
        config.setCustomerName("Amruta");
        config.setUserSearchKeywords("AmrutaChichani");
        return config;
    }
}

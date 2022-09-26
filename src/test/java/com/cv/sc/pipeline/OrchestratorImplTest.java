package com.cv.sc.pipeline;

import com.cv.sc.model.Config;
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

        Map<String, List<GitHubEntity>> result = orchestrator.search(config);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.containsKey("CodeResult"));
        Assert.assertTrue(result.containsKey("UserResult"));
        Assert.assertTrue(result.containsKey("FileResult"));
      /*  Assert.assertTrue(result.containsKey("RepoResult"));*/
    }

    private Config getConfig() {
        Config config = new Config();
        config.setId(1L);
        config.setConfigName("test1");
        config.setCodeSearchKeywords("CountAboveSixty");
        config.setCustomerName("Amruta");
        config.setUserSearchKeywords("AmrutaChichani");
        config.setRepositoryNames("AmrutaChichani/Java_8_demo_project");
        return config;
    }
}

package com.cv.sc.pipeline.scheduler;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.pipeline.OrchestratorImpl;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.IOException;
import java.util.List;

@Slf4j
public class SearchJob implements Job {
    private final OrchestratorImpl orchestrator = OrchestratorImpl.getInstance();

    private final DBStorageServiceImpl storageService = DBStorageServiceImpl.getInstance();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Config> scheduledConfigList = (List<Config>) storageService.getScheduledConfig();
        for (Config configItem: scheduledConfigList) {
            try {
                 orchestrator.search(configItem);
                log.info("running search job for " + configItem.getConfigName() + " @ " + DateTime.now());
            } catch (HttpClientException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

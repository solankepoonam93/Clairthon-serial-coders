package com.cv.sc.pipeline.scheduler;

import com.cv.sc.exception.HttpClientException;
import com.cv.sc.model.Config;
import com.cv.sc.model.SearchResponse;
import com.cv.sc.pipeline.OrchestratorImpl;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.SQLOutput;

public class SearchJob implements Job {
    private OrchestratorImpl orchestrator = OrchestratorImpl.getInstance();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap configDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Config config = (Config) configDataMap.get("config");
        try {
          SearchResponse jobResponse = orchestrator.search(config);
           //Todo
            // save response in database.
            System.out.println("running search job for " + config.getConfigName() + " @ " + DateTime.now());
        } catch (HttpClientException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}

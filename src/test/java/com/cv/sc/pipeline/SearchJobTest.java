package com.cv.sc.pipeline;

import com.cv.sc.pipeline.scheduler.SearchJob;
import org.junit.Assert;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SearchJobTest {
    SearchJob searchJob = new SearchJob();

    @Test
    public void createJobTest() {
        JobDetail jobDetail = JobBuilder.newJob(SearchJob.class)
                .withIdentity("job1")
                .build();
        Assert.assertNotNull(jobDetail);
    }

    @Test
    public void executeJobTest() throws SchedulerException, InterruptedException {
        JobDetail jobDetail = JobBuilder.newJob(SearchJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1).withRepeatCount(1)).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        Date schedulerDate = scheduler.scheduleJob(jobDetail, trigger);
        Assert.assertNotNull(schedulerDate);
    }
}

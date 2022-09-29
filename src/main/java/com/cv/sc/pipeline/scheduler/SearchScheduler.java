package com.cv.sc.pipeline.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.Assert;

@Slf4j
public class SearchScheduler {
    private static SearchScheduler instance;

    public static SearchScheduler getInstance() {
        if(instance == null) {
            instance = new SearchScheduler();
        }
        return instance;
    }

    String cronInterval = System.getenv("quartz.cron.interval");

    public void createJobScheduler() throws SchedulerException {

        Assert.notNull(cronInterval,"Set up cron interval in environment variable.");
        JobDetail jobDetail = JobBuilder.newJob(SearchJob.class)
                .withIdentity("job1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1").startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(Integer.parseInt(cronInterval)).repeatForever())
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("Scheduler created");
    }
}

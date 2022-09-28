package com.cv.sc.pipeline.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SearchScheduler {
    private static SearchScheduler instance;


    public static SearchScheduler getInstance() {
        if(instance == null) {
            instance = new SearchScheduler();
        }
        return instance;
    }

    public void createJobScheduler() throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(SearchJob.class)
                .withIdentity("job1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1").startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(1).repeatForever())
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}

package com.cv.sc.POC;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerPOC {
    public static void main(String args[]) {
        try {

            JobDetail jobDetail = JobBuilder.newJob(CronJob.class)
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
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}

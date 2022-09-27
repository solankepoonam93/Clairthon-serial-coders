package com.cv.sc.web.listener;

import com.cv.sc.model.Config;
import com.cv.sc.pipeline.scheduler.SearchJob;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SCApplicationListener implements ApplicationListener {

    private DBStorageServiceImpl storageService = new DBStorageServiceImpl();

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof ApplicationStartedEvent) {
                ApplicationContext applicationContext = ((ApplicationStartedEvent) event).getApplicationContext();
                createScheduler();
            }
        }

        private void createScheduler() {
            try {
                List<Config> scheduledConfigList =(List<Config>) storageService.getScheduledConfig();
                for (Config configItem: scheduledConfigList) {
                    JobDataMap dataMap = new JobDataMap();
                    dataMap.put("config", configItem);
                    JobDetail jobDetail = JobBuilder.newJob(SearchJob.class)
                            .withIdentity("job_" + configItem.getId())
                            .usingJobData(dataMap)
                            .build();

                    Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity("trigger_" + configItem.getId()).startNow()
                            .withSchedule(
                                    SimpleScheduleBuilder.simpleSchedule()
                                            .withIntervalInMinutes(1).repeatForever())
                            .build();

                    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                    Scheduler scheduler = schedulerFactory.getScheduler();
                    scheduler.start();
                    scheduler.scheduleJob(jobDetail, trigger);
                }
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
    }

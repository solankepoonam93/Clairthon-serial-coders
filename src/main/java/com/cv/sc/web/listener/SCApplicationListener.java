package com.cv.sc.web.listener;

import com.cv.sc.pipeline.scheduler.SearchScheduler;
import org.quartz.*;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class SCApplicationListener implements ApplicationListener<ApplicationEvent> {

  private final SearchScheduler searchScheduler = SearchScheduler.getInstance();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            try {
                searchScheduler.createJobScheduler();
            } catch (SchedulerException e) {
                System.out.println("Error: Can not start scheduler." + e);
                e.printStackTrace();
            }
        }
    }

}

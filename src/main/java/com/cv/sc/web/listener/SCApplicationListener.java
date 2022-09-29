package com.cv.sc.web.listener;

import com.cv.sc.pipeline.scheduler.SearchScheduler;
import com.cv.sc.util.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SCApplicationListener implements ApplicationListener<ApplicationEvent> {

  private final SearchScheduler searchScheduler = SearchScheduler.getInstance();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            try {
                searchScheduler.createJobScheduler();
            } catch (SchedulerException e) {
                log.error(ExceptionConstants.EXCEPTION_SCHEDULER_FAILURE);
                e.printStackTrace();
            }
        }
    }

}

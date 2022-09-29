package com.cv.sc.web;

import static org.mockito.Mockito.*;

import com.cv.sc.pipeline.scheduler.SearchScheduler;
import com.cv.sc.web.listener.SCApplicationListener;
import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.SchedulerException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;

public class SCApplicationListenerTest {
    SearchScheduler searchScheduler = mock(SearchScheduler.class);
    ApplicationEvent event = mock(ApplicationStartedEvent.class);
    SCApplicationListener scApplicationListener = new SCApplicationListener();

    @Test
    public void whenCreateSchedulerSuccessTest() throws SchedulerException {
        doNothing().when(searchScheduler).createJobScheduler();
        scApplicationListener.onApplicationEvent(event);
        Mockito.verify(searchScheduler,times(0)).createJobScheduler();
    }
}

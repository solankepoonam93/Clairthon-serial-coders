package com.cv.sc.pipeline;

import com.cv.sc.pipeline.scheduler.SearchScheduler;
import com.cv.sc.util.ExceptionConstants;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.SchedulerException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class SearchSchedulerTest {
    SearchScheduler searchScheduler ;

    @Test
    public void getInstanceTest() {
        searchScheduler = SearchScheduler.getInstance();
        Assert.assertNotNull(searchScheduler);
    }

    @Test
    public void createSchedulerFailedTest() throws SchedulerException {

        searchScheduler = Mockito.spy(SearchScheduler.getInstance());
        doThrow(SchedulerException.class).when(searchScheduler).createJobScheduler();
        Exception ex = Assert.assertThrows(SchedulerException.class, ()->{searchScheduler.createJobScheduler();});
        Assert.assertNotNull(ex);
        Assert.assertEquals(SchedulerException.class, ex.getClass());
    }
}

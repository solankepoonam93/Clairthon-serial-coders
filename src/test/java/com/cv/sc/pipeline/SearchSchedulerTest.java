package com.cv.sc.pipeline;

import com.cv.sc.model.SearchResponse;
import com.cv.sc.pipeline.scheduler.SearchScheduler;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.SchedulerException;
import java.util.List;

import static org.mockito.Mockito.doThrow;

public class SearchSchedulerTest {
    SearchScheduler searchScheduler ;

    @Test
    public void getInstanceTest() {
        searchScheduler = SearchScheduler.getInstance();
        Assert.assertNotNull(searchScheduler);
    }

    @Test
    public void createSchedulerSuccessTest() throws InterruptedException, SchedulerException {
        DBStorageServiceImpl dbStorageService = DBStorageServiceImpl.getInstance();
        List priorList = dbStorageService.findAll(SearchResponse.class);
        searchScheduler = SearchScheduler.getInstance();
        searchScheduler.createJobScheduler();
        Thread.sleep(70000);
        List postList = dbStorageService.findAll(SearchResponse.class);
        Assert.assertTrue(postList.size()> priorList.size());
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

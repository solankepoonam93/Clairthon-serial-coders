package com.cv.sc.controller;

import com.cv.sc.models.ApiResponse;
import com.cv.sc.models.SearchResult;
import com.cv.sc.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @PostMapping("/saveResult")
    public ApiResponse saveResult(@RequestBody SearchResult resultModel) {
        log.info("saveResult is called: "+resultModel);
        return testService.saveResult(resultModel);
    }
}

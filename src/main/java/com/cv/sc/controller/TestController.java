package com.cv.sc.controller;

import com.cv.sc.models.ApiResponse;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private StorageService storageService;

}

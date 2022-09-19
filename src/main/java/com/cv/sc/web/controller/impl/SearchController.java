package com.cv.sc.web.controller.impl;

import com.cv.sc.models.APIResponse;
import com.cv.sc.models.Config;
import com.cv.sc.web.controller.SCController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@RestController
@RequestMapping("/search")
public class SearchController implements SCController {
    @RequestMapping(path = "/allResults", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse search(Config config) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResponseStatus(HttpStatus.OK);
        apiResponse.setResponse("Not yet implemented");
        return apiResponse;
    }
}

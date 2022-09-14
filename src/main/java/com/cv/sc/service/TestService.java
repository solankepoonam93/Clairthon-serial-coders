package com.cv.sc.service;

import com.cv.sc.models.ApiResponse;
import com.cv.sc.models.SearchResult;

public interface TestService {

    public ApiResponse saveResult(SearchResult resultModel);
}

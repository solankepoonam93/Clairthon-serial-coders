package com.cv.sc.service;

import com.cv.sc.models.ApiResponse;
import com.cv.sc.models.ResultModel;

public interface TestService {

    public ApiResponse saveResult(ResultModel resultModel);
}

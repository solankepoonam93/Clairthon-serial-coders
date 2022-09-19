package com.cv.sc.web.filter.utils;

import com.cv.sc.model.APIResponse;
import org.springframework.http.HttpStatus;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public class WebUtils {
    private WebUtils() {
        // all static
    }

    public static APIResponse buildAPIResponse(HttpStatus responseStatus, Object response, String errorMessage) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResponseStatus(responseStatus);
        apiResponse.setResponse(response);
        apiResponse.setErrorMessage(errorMessage);
        return apiResponse;
    }
}

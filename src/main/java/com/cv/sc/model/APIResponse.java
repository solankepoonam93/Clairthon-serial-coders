package com.cv.sc.model;

import org.springframework.http.HttpStatus;

/**
 * Response of the REST api
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public class APIResponse implements SCEntity {
    private HttpStatus responseStatus;
    private Object response;
    private String errorMessage;

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

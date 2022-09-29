package com.cv.sc.util;

public class ExceptionConstants {

    private ExceptionConstants() {
    }
    public static final String EXCEPTION_SCHEDULER_FAILURE = "Can not start scheduler";
    public static final String EXCEPTION_HTTP_METHOD_NOT_SUPPORTED = "HTTP Method not supported!";
    public static final String EXCEPTION_UNAUTHORIZED_TOKEN = "You are not Authorized to use this API. Please pass valid token.";
    public static final String EXCEPTION_INVALID_TOKEN = "Token not present or it is invalid";
    public static final String EXCEPTION_INVALID_TOKEN_GENERATE_NEW = "Invalid token. Please login again and get a new valid token";
    public static final String EXCEPTION_SOMETHING_WENT_WRONG = "Something went wrong during authentication";
}


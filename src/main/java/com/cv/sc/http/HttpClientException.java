package com.cv.sc.http;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 12/09/22
 */
public class HttpClientException extends Exception {
    public HttpClientException() {
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

    public HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

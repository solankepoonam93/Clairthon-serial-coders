package com.cv.sc.exception;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/09/22
 */
public class IllegalTypeException extends IllegalArgumentException {
    public IllegalTypeException() {
    }

    public IllegalTypeException(String s) {
        super(s);
    }

    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTypeException(Throwable cause) {
        super(cause);
    }
}

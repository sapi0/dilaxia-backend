package com.sapi0.dilaxiabackend.exception;

public class AccessException extends EndpointException {

    public AccessException() {
        super();
    }
    public AccessException(int statusCode, String message) {
        super(statusCode, message);
    }

}
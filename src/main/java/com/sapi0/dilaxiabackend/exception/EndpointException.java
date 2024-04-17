package com.sapi0.dilaxiabackend.exception;

public class EndpointException extends Exception {

    public int statusCode = 400;

    public EndpointException() {
        super();
    }
    public EndpointException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

}

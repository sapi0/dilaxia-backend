package com.sapi0.dilaxiabackend.exception;

public class IllegalParamException extends EndpointException {

    public IllegalParamException() {
        super();
    }
    public IllegalParamException(int statusCode, String message) {
        super(statusCode, message);
    }

}

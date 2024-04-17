package com.sapi0.dilaxiabackend.exception;

public class PathParamParseException extends EndpointException {

    public PathParamParseException() {
        super();
    }
    public PathParamParseException(int statusCode, String message) {
        super(statusCode, message);
    }

}

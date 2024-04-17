package com.sapi0.dilaxiabackend.exception;

public class BodyParseException extends EndpointException {

    public BodyParseException() {
        super();
    }
    public BodyParseException(int statusCode, String message) {
        super(statusCode, message);
    }

}

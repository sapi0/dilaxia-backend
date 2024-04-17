package com.sapi0.dilaxiabackend.exception;

public class QueryParamParseException extends EndpointException {

    public QueryParamParseException() {
        super();
    }
    public QueryParamParseException(int statusCode, String message) {
        super(statusCode, message);
    }

}

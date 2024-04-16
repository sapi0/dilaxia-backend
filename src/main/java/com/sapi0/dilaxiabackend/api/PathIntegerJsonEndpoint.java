package com.sapi0.dilaxiabackend.api;

import com.sapi0.dilaxiabackend.exception.PathParamParseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class PathIntegerJsonEndpoint extends JsonEndpoint {

    public abstract void init();
    public abstract void destroy();

    private int extractPathParam(HttpServletRequest request) throws PathParamParseException {
        String pathInfo = request.getPathInfo();

        try {
            String s = pathInfo.split("/")[1];
            return Integer.parseInt(s);
        } catch(Exception e) {
            throw new PathParamParseException("Invalid path param");
        }
    }

    @Override
    protected JSONObject get(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> headers) throws PathParamParseException {
        return get(headers, extractPathParam(request));
    }
    protected JSONObject get(HashMap<String, String> headers, int pathParam) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) throws PathParamParseException {
        return post(bodyObject, headers, extractPathParam(request));
    }
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, int pathParam) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject put(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) throws PathParamParseException {
        return put(bodyObject, headers, extractPathParam(request));
    }
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, int pathParam) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject delete(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) throws PathParamParseException {
        return delete(bodyObject, headers, extractPathParam(request));
    }
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, int pathParam) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

}

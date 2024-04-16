package com.sapi0.dilaxiabackend.api;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public abstract class BasicJsonEndpoint extends JsonEndpoint {

    public abstract void init();
    public abstract void destroy();

    @Override
    protected JSONObject get(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> headers) {
        return get(headers);
    }
    protected JSONObject get(HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return post(bodyObject, headers);
    }
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject put(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return put(bodyObject, headers);
    }
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject delete(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return delete(bodyObject, headers);
    }
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

}

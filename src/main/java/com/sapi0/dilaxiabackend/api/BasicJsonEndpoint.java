package com.sapi0.dilaxiabackend.api;

import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public abstract class BasicJsonEndpoint extends JsonEndpoint {

    public abstract void init();
    public abstract void destroy();

    @Override
    protected JSONObject get(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return get(headers, queryParams, request.getSession(false));
    }
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return post(bodyObject, headers, queryParams, request.getSession(false));
    }
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject put(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return put(bodyObject, headers, queryParams, request.getSession(false));
    }
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

    @Override
    protected JSONObject delete(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return delete(bodyObject, headers, queryParams, request.getSession(false));
    }
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

}

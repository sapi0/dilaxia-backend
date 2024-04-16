package com.sapi0.dilaxiabackend.api;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

public abstract class JsonEndpoint extends HttpServlet {

    protected static final JSONObject NOT_IMPLEMENTED_ERROR_JSON = new JSONObject().put("error", "Not implemented.");

    public abstract void init();
    public abstract void destroy();

    private HashMap<String, String> findHeaders(HttpServletRequest request) {
        HashMap<String, String> headers = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();

        if(headerNames != null) {
            while(headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.put(headerName, request.getHeader(headerName));
            }
        }

        return headers;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = get(request, response, headers);

        response.getWriter().print(result.toString());
        response.getWriter().flush();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = post(request, response, bodyObject, headers);

        response.getWriter().print(result.toString());
        response.getWriter().flush();
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = put(request, response, bodyObject, headers);

        response.getWriter().print(result.toString());
        response.getWriter().flush();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = delete(request, response, bodyObject, headers);

        response.getWriter().print(result.toString());
        response.getWriter().flush();
    }

    protected JSONObject get(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject put(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject delete(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers) {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

}

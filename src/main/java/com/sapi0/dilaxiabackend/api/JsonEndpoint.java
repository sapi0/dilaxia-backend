package com.sapi0.dilaxiabackend.api;

import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.exception.QueryParamParseException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

public abstract class JsonEndpoint extends HttpServlet {

    protected static final JSONObject DEFAULT_SUCCESS_JSON = new JSONObject().put("message", "Success!");
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

    private HashMap<String, String> findQueryParams(HttpServletRequest request) throws QueryParamParseException {
        HashMap<String, String> queries = new HashMap<>();

        String queryString = request.getQueryString();

        if(queryString != null) {
            String[] params = queryString.split("&");

            for (String param : params) {
                try {
                    String[] data = param.split("=");
                    queries.put(data[0], data[1]);
                } catch (Exception e) {
                    throw new QueryParamParseException(499, "Failed to parse query params! Bad param: " + param);
                }
            }
        }

        return queries;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = null;
        try {
            result = get(request, response, headers, findQueryParams(request));
        } catch (EndpointException e) {
            response.setStatus(e.statusCode);
            result = new JSONObject().put("error", e.getMessage());
        } catch(Exception e) {
            response.sendError(500, e.getMessage());
            return;
        }

        response.getWriter().print(result);
        response.getWriter().flush();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = null;
        try {
            result = post(request, response, bodyObject, headers, findQueryParams(request));
        } catch (EndpointException e) {
            response.setStatus(e.statusCode);
            result = new JSONObject().put("error", e.getMessage());
        } catch(Exception e) {
            response.sendError(500, e.getMessage());
            return;
        }

        response.getWriter().print(result);
        response.getWriter().flush();
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = null;
        try {
            result = put(request, response, bodyObject, headers, findQueryParams(request));
        } catch (EndpointException e) {
            response.setStatus(e.statusCode);
            result = new JSONObject().put("error", e.getMessage());
        } catch(Exception e) {
            response.sendError(500, e.getMessage());
            return;
        }

        response.getWriter().print(result);
        response.getWriter().flush();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String rawBody = IOUtils.toString(request.getReader());
        JSONObject bodyObject = new JSONObject(rawBody);

        HashMap<String, String> headers = findHeaders(request);

        JSONObject result = null;
        try {
            result = delete(request, response, bodyObject, headers, findQueryParams(request));
        } catch (EndpointException e) {
            response.setStatus(e.statusCode);
            result = new JSONObject().put("error", e.getMessage());
        } catch(Exception e) {
            response.sendError(500, e.getMessage());
            return;
        }

        response.getWriter().print(result);
        response.getWriter().flush();
    }

    protected JSONObject get(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject put(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }
    protected JSONObject delete(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        return NOT_IMPLEMENTED_ERROR_JSON;
    }

}

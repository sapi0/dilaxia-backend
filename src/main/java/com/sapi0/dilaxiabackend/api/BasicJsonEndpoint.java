package com.sapi0.dilaxiabackend.api;

import com.sapi0.dilaxiabackend.exception.AccessException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.exception.IllegalParamException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

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

    protected String assertParam(String param, int minLen, int maxLen, int errorCode) throws IllegalParamException {
        if(param == null) throw new IllegalParamException(errorCode, "Required param is not set");
        if(param.length() < minLen) throw new IllegalParamException(errorCode, "Param too short");
        if(param.length() > maxLen) throw new IllegalParamException(errorCode, "Param too long");
        return param;
    }

    protected void requireLoggedOut(HttpSession session) throws AccessException {
        if(session != null) {
            if(session.getAttribute("logged") != null) // tanto non puo' mai essere logged=false... o e' true o non c'e'
                throw new AccessException(499, "Already logged in.");
        }
    }

    protected void requireLoggedIn(HttpSession session) throws AccessException {
        if(session == null || session.getAttribute("logged") == null) throw new AccessException(499, "Not logged in.");
    }

}

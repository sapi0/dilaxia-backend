package com.sapi0.dilaxiabackend.utils;

import com.sapi0.dilaxiabackend.exception.IllegalParamException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.HashMap;

public class Params {

    public static int queryToInt(HashMap<String, String> queryParams, String param, int defaultParam) throws IllegalParamException {
        int result = defaultParam;
        if(queryParams.get(param) != null) {
            try {
                result = Integer.parseInt(queryParams.get(param));
            } catch(Exception e) {
                throw new IllegalParamException(499, param + " non e' un numero valido");
            }
        }
        return result;
    }

    public static boolean queryToBool(HashMap<String, String> queryParams, String param, boolean defaultParam) throws IllegalParamException {
        boolean result = defaultParam;
        if(queryParams.get(param) != null) {
            try {
                result = Boolean.parseBoolean(queryParams.get(param));
            } catch(Exception e) {
                throw new IllegalParamException(499, param + " non e' un boolean");
            }
        }
        return result;
    }

    public static DateTime queryToDate(HashMap<String, String> queryParams, String param, DateTime defaultParam) throws IllegalParamException {
        DateTime result = null;
        if(queryParams.get(param) != null) {
            try {
                result = DateTimeFormat.forPattern("dd/MM/uuuu").parseDateTime(queryParams.get(param));
            } catch(Exception e) {
                throw new IllegalParamException(499, param + " non e' una data valida");
            }
        }
        return result;
    }
}

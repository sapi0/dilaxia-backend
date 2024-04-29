package com.sapi0.dilaxiabackend.utils;

import com.sapi0.dilaxiabackend.exception.IllegalParamException;

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

}

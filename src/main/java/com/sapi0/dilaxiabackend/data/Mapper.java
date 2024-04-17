package com.sapi0.dilaxiabackend.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class Mapper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String asJSON(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T asObject(String json, Class<T> tClass) throws JsonProcessingException {
        return mapper.readValue(json, tClass);
    }

    public static <T> T asObject(JSONObject json, Class<T> tClass) throws JsonProcessingException {
        return asObject(json.toString(), tClass);
    }

}

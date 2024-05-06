package com.sapi0.dilaxiabackend.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sapi0.dilaxiabackend.data.dto.UserUpdateDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import org.json.JSONObject;

public class Mapper {

    private final static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.registerModule(new JodaModule());
    }

    public static JSONObject asJSON(Object obj) throws JsonProcessingException {
        return new JSONObject(mapper.writeValueAsString(obj));
    }

    public static <T> T asObject(String json, Class<T> tClass) throws JsonProcessingException {
        return mapper.readValue(json, tClass);
    }

    public static <T> T asObject(JSONObject json, Class<T> tClass) throws JsonProcessingException {
        return asObject(json.toString(), tClass);
    }

    public static <T> T update(T dest, Object in) throws JsonProcessingException {
        return mapper.readerForUpdating(dest).readValue(mapper.writeValueAsString(in));
    }
}

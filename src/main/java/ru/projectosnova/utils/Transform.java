package ru.projectosnova.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Transform {

    public static <T> T jsonToObject(String json, Class<T> targetClass){
        ObjectMapper mapper = new ObjectMapper();
        T result;
        try {
            result = mapper.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}

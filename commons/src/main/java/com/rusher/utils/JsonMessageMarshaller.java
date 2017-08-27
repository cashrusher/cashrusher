package com.rusher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.ws.WebServiceMessageMarshaller;

/**
 * Author: Liam
 * Date: 2016/9/30
 */
public class JsonMessageMarshaller extends WebServiceMessageMarshaller {
    private Class clazz;
    private ObjectMapper mapper;

    public JsonMessageMarshaller() {
    }

    public JsonMessageMarshaller(Class clazz, ObjectMapper objectMapper) {
        this.clazz = clazz;
        this.mapper = objectMapper;
    }

    @Override
    public String marshal(Object object) throws Exception {
        return mapper.writeValueAsString(object);
    }

    @Override
    public Object doUnmarshal(String value) throws Exception {
        return mapper.readValue(value, clazz);
    }

    public Object doUnmarshal(String value, Class clazz) throws Exception {
        return mapper.readValue(value, clazz);
    }
}

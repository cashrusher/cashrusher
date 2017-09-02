package com.rusher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.ws.WebServiceMessageMarshaller;

/**
 * Author: Liam
 * Date: 2017/8/30
 */
public class JsonMessageMarshaller extends WebServiceMessageMarshaller {
    private ObjectMapper mapper;

    public JsonMessageMarshaller() {
    }

    public JsonMessageMarshaller(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public String marshal(Object object) throws Exception {
        return mapper.writeValueAsString(object);
    }

    @Override
    public Object doUnmarshal(String value, Class clazz) throws Exception {
        return mapper.readValue(value, clazz);
    }
}

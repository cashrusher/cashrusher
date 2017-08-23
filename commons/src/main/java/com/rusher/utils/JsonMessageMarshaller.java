package com.rusher.utils;

import com.alibaba.fastjson.JSON;
import com.rusher.ws.WebServiceMessageMarshaller;

/**
 * Author: Liam
 * Date: 2016/9/30
 */
public class JsonMessageMarshaller extends WebServiceMessageMarshaller {
    private Class clazz;

    public JsonMessageMarshaller() {
    }

    public JsonMessageMarshaller(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String marshal(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public Object doUnmarshal(String value) throws Exception {
        return JSON.parseObject(value, clazz);
    }
}

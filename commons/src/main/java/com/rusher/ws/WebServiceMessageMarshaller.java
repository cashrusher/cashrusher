package com.rusher.ws;


public abstract class WebServiceMessageMarshaller {
    public abstract String marshal(Object object) throws Exception;

    public abstract Object doUnmarshal(String value, Class clazz) throws Exception;
}

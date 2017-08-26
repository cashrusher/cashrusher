package com.rusher.ws;


public abstract class WebServiceMessageMarshaller {
    public abstract String marshal(Object object) throws Exception;

    public Object unmarshal(String value) {
        if (value == null) {
            return null;
        }

        try {
            return doUnmarshal(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object doUnmarshal(String value) throws Exception;
}

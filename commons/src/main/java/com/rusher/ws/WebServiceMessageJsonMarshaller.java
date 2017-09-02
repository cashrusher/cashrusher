package com.rusher.ws;


public class WebServiceMessageJsonMarshaller extends WebServiceMessageMarshaller {
    private final Class responseClass;

    public WebServiceMessageJsonMarshaller(Class responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public String marshal(Object object) {
//        return JsonUtils.toJson(object);
        return null;
    }

    @Override
    public Object doUnmarshal(String value, Class clazz) {
//        return JsonUtils.toObject(value, responseClass);
        return null;

    }
}

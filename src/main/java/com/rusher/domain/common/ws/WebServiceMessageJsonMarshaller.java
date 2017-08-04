package com.rusher.domain.common.ws;

import com.derbysoft.warrior.common.json.JsonUtils;

public class WebServiceMessageJsonMarshaller extends WebServiceMessageMarshaller {
    private final Class responseClass;

    public WebServiceMessageJsonMarshaller(Class responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public String marshal(Object object) {
        return JsonUtils.toJson(object);
    }

    @Override
    protected Object doUnmarshal(String value) {
        return JsonUtils.toObject(value, responseClass);
    }
}

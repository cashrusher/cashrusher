package com.rusher.domain.common.ws;

import com.derbysoft.warrior.common.exception.MessageUnmarshalException;

public abstract class WebServiceMessageMarshaller {
    public abstract String marshal(Object object);

    public Object unmarshal(String value) {
        if (value == null) {
            return null;
        }

        try {
            return doUnmarshal(value);
        } catch (Exception e) {
            throw new MessageUnmarshalException(e);
        }
    }

    protected abstract Object doUnmarshal(String value) throws Exception;
}

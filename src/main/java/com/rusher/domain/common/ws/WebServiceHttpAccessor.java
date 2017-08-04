package com.rusher.domain.common.ws;

import com.derbysoft.warrior.common.exception.HttpErrorException;
import com.derbysoft.warrior.common.exception.MessageUnmarshalException;
import com.derbysoft.warrior.common.http.HttpErrorUnmarshaller;
import com.derbysoft.warrior.common.http.HttpStatusCodes;

public class WebServiceHttpAccessor extends WebServiceAccessor {
    private final WebServiceMessageHttpSender sender;
    private HttpErrorUnmarshaller httpErrorUnmarshaller;

    public WebServiceHttpAccessor(String url, WebServiceMessageMarshaller marshaller, WebServiceMessageHttpSender webServiceMessageSender) {
        super(url, marshaller, webServiceMessageSender);
        this.sender = webServiceMessageSender;
    }

    public void setHttpErrorUnmarshaller(HttpErrorUnmarshaller httpErrorUnmarshaller) {
        this.httpErrorUnmarshaller = httpErrorUnmarshaller;
    }

    @Override
    protected <RS> RS unmarshal(String rsMessage) {
        final int responseCode = sender.getResponseCode();
        if (responseCode != HttpStatusCodes.SUCCESS && httpErrorUnmarshaller != null) {
            return (RS) httpErrorUnmarshaller.unmarshal(responseCode, rsMessage);
        }

        try {
            return super.unmarshal(rsMessage);
        } catch (MessageUnmarshalException e) {
            if (responseCode != HttpStatusCodes.SUCCESS) {
                throw new HttpErrorException(responseCode, rsMessage);
            }
            throw e;
        }
    }
}
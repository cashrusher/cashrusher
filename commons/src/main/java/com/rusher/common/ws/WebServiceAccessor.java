package com.rusher.common.ws;


public abstract class WebServiceAccessor {
    private final String url;
    private final WebServiceMessageMarshaller marshaller;
    private final WebServiceMessageSender webServiceMessageSender;

    public WebServiceAccessor(String url, WebServiceMessageMarshaller marshaller, WebServiceMessageSender webServiceMessageSender) {
        this.url = url;
        this.marshaller = marshaller;
        this.webServiceMessageSender = webServiceMessageSender;
    }

//    public <RQ, RS> RS send(final RQ rq) {
//        return send(rq, null, null);
//    }
//
//    public <RQ, RS> RS send(final RQ rq, final Map<String, String> httpHeader) {
//        return send(rq, httpHeader, null);
//    }

//    public <RQ, RS> RS send(final RQ rq, final Matcher<Exception> retryMatcher) {
//        return send(rq, null, retryMatcher);
//    }
//
//    public <RQ, RS> RS send(final RQ rq, final Map<String, String> httpHeader, final Matcher<Exception> retryMatcher) {
//        final String rqMessage = beforeSend(marshaller.marshal(rq));
//        final String rsMessage = afterSend(webServiceMessageSender.send(url, rqMessage, httpHeader));
//        return unmarshal(rsMessage);
//    }

    protected <RS> RS unmarshal(String rsMessage) {
        return (RS) marshaller.unmarshal(rsMessage);
    }

    protected String beforeSend(String rqMessage) {
        return rqMessage;
    }

    protected String afterSend(String rsMessage) {
        return rsMessage;
    }
}
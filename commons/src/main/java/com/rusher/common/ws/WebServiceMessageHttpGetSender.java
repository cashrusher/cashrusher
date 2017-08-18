package com.rusher.common.ws;


import javax.net.ssl.SSLSocketFactory;
import java.util.Map;

public class WebServiceMessageHttpGetSender extends WebServiceMessageHttpSender {
    private static final String METHOD = "GET";

    public WebServiceMessageHttpGetSender(String contentType, int connectionTimeout, int readTimeout) {
        super(contentType, connectionTimeout, readTimeout);
    }

    public WebServiceMessageHttpGetSender(String contentType, String charset, int connectionTimeout, int readTimeout) {
        super(contentType, charset, connectionTimeout, readTimeout);
    }

    public WebServiceMessageHttpGetSender(String contentType, String charset, int connectionTimeout, int readTimeout, SSLSocketFactory sslSocketFactory) {
        super(contentType, charset, connectionTimeout, readTimeout, sslSocketFactory);
    }

    @Override
    protected String getRequestMethod() {
        return METHOD;
    }

    public String send(String url, Map<String, Object> request) {
        return send(url, request, null);
    }

    public String send(String url, Map<String, Object> request, Map<String, String> httpHeader) {
        return send(url + "?" + HttpRequestHelper.toRequestMessage(request, charset), (String) null, httpHeader);
    }

    public String send(String url) {
        return null;
    }

    public String send(String url, String rqMessage) {
        return null;
    }

    public String send(String url, String rqMessage, Map<String, String> httpHeader) {
        return null;
    }
}
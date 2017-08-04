package com.rusher.domain.common.ws;

import com.derbysoft.warrior.common.http.HttpRequestHelper;

import javax.net.ssl.SSLSocketFactory;
import java.util.Map;

public class WebServiceMessageHttpPostSender extends WebServiceMessageHttpSender {
    private static final String METHOD = "POST";

    public WebServiceMessageHttpPostSender(String contentType, int connectionTimeout, int readTimeout) {
        super(contentType, connectionTimeout, readTimeout);
    }

    public WebServiceMessageHttpPostSender(String contentType, String charset, int connectionTimeout, int readTimeout) {
        super(contentType, charset, connectionTimeout, readTimeout);
    }

    public WebServiceMessageHttpPostSender(String contentType, String charset, int connectionTimeout, int readTimeout, SSLSocketFactory sslSocketFactory) {
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
        return send(url, HttpRequestHelper.toRequestMessage(request, charset), httpHeader);
    }
}
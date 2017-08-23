package com.rusher.ws;


import com.rusher.http.HttpHeaderHelper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

public class WebServiceRequestMessageReader {
    private static final WebServiceRequestMessageFactory DEFAULT_WEB_SERVICE_REQUEST_MESSAGE_FACTORY = new WebServiceRequestMessageFactory() {
        @Override
        public WebServiceRequestMessage createGZipMessage(String uri, String contentType, Charset charset, byte[] data, HttpServletRequest httpServletRequest) {
            return new WebServiceRequestMessage(uri, contentType, charset, true, data);
        }

        @Override
        public WebServiceRequestMessage createNonGZipMessage(String uri, String contentType, Charset charset, byte[] data, HttpServletRequest httpServletRequest) {
            return new WebServiceRequestMessage(uri, contentType, charset, false, data);
        }
    };

    private final Charset defaultCharset;
    private final WebServiceRequestMessageFactory webServiceRequestMessageFactory;

    public WebServiceRequestMessageReader(String defaultCharset) {
        this(defaultCharset, DEFAULT_WEB_SERVICE_REQUEST_MESSAGE_FACTORY);
    }

    public WebServiceRequestMessageReader(String defaultCharset, WebServiceRequestMessageFactory webServiceRequestMessageFactory) {
        this.defaultCharset = Charset.forName(defaultCharset);
        this.webServiceRequestMessageFactory = webServiceRequestMessageFactory;
    }

    public Charset getRequestCharset(HttpServletRequest httpServletRequest) {
        return HttpHeaderHelper.getCharset(httpServletRequest, defaultCharset);
    }

    public WebServiceRequestMessage read(HttpServletRequest httpServletRequest) {
        final String uri = HttpRequestHelper.getRequestUri(httpServletRequest);
        final Charset requestCharset = getRequestCharset(httpServletRequest);
        final String contentType = HttpHeaderHelper.getContentType(httpServletRequest);
        if (HttpHeaderHelper.isGZipContent(httpServletRequest)) {
            return webServiceRequestMessageFactory.createGZipMessage(uri, contentType, requestCharset, getContent(httpServletRequest), httpServletRequest);
        } else {
            return webServiceRequestMessageFactory.createNonGZipMessage(uri, contentType, requestCharset, getContent(httpServletRequest), httpServletRequest);
        }
    }

    private byte[] getContent(HttpServletRequest httpServletRequest) {
        try {
            return HttpRequestHelper.getContent(httpServletRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
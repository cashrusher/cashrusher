package com.rusher.ws;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

public interface WebServiceRequestMessageFactory<T extends WebServiceRequestMessage> {
    T createGZipMessage(String uri, String contentType, Charset charset, byte[] data, HttpServletRequest httpServletRequest);

    T createNonGZipMessage(String uri, String contentType, Charset charset, byte[] data, HttpServletRequest httpServletRequest);
}

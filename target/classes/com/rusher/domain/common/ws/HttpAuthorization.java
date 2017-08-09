package com.rusher.domain.common.ws;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import static com.rusher.domain.common.utils.Base64Utils.toBase64;

public class HttpAuthorization {
    private static final int HTTP_AUTHORIZATION_ERROR_CODE = 401;
    private static final String HTTP_AUTHORIZATION_ERROR_MESSAGE = "Authentication is required";
    private static final String HTTP_AUTHORIZATION_ERROR_RESPONSE_HEADER_KEY = "WWW-Authenticate";
    private static final String HTTP_AUTHORIZATION_ERROR_RESPONSE_HEADER_VALUE = "Basic realm=\"" + HTTP_AUTHORIZATION_ERROR_MESSAGE + "\"";

    private final String basic64UsernamePassword;
    private boolean test = false;

    public HttpAuthorization(String username, String password) {
        this.basic64UsernamePassword = "Basic " + toBase64((username.trim() + ":" + password.trim()).getBytes());
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean validate(HttpServletRequest httpServletRequest) {
        if (test) {
            return true;
        }
        final String rqBasic64UsernamePassword = httpServletRequest.getHeader(HttpHeaderNames.AUTHORIZATION);
        return StringUtils.isNotBlank(rqBasic64UsernamePassword) && rqBasic64UsernamePassword.equals(this.basic64UsernamePassword);
    }

    public boolean validate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (!validate(httpServletRequest)) {
            httpServletResponse.setHeader(HTTP_AUTHORIZATION_ERROR_RESPONSE_HEADER_KEY, HTTP_AUTHORIZATION_ERROR_RESPONSE_HEADER_VALUE);
            httpServletResponse.sendError(HTTP_AUTHORIZATION_ERROR_CODE, HTTP_AUTHORIZATION_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void addToRequestHeader(HttpURLConnection connection) {
        connection.setRequestProperty(HttpHeaderNames.AUTHORIZATION, basic64UsernamePassword);
    }
}
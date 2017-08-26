package com.rusher.ws;

import com.rusher.exception.HttpErrorException;
import com.rusher.http.HttpHeaderHelper;
import com.rusher.http.HttpStatusCodes;

import javax.servlet.http.HttpServletRequest;

public class WebServiceRequestValidator {
    private HttpAuthorization authorization;
    private boolean gzipRequired = false;
    private int requestLarge = 0;

    public void setAuthorization(HttpAuthorization authorization) {
        this.authorization = authorization;
    }

    public void setGzipRequired(boolean gzipRequired) {
        this.gzipRequired = gzipRequired;
    }

    public void setRequestLarge(int requestLarge) {
        this.requestLarge = requestLarge;
    }

    public void validate(HttpServletRequest httpServletRequest) {
        if (authorization != null && !authorization.validate(httpServletRequest)) {
            throw new HttpErrorException(HttpStatusCodes.UNAUTHORIZED, "Invalid username or password");
        }

        if (gzipRequired && !HttpHeaderHelper.isGZipContent(httpServletRequest)) {
            throw new HttpErrorException(HttpStatusCodes.BAD_REQUEST, "GZip is required");
        }

//        if (!HttpHeaderHelper.hasContentLength(httpServletRequest)) {
//            throw new HttpErrorException(HttpStatusCodes.CONTENT_LENGTH_REQUIRED, "Content-Length is required");
//        }

//        try {
//            final int contentLength = HttpHeaderHelper.getContentLength(httpServletRequest);
//            if (contentLength < 1) {
//                throw new HttpErrorException(HttpStatusCodes.CONTENT_LENGTH_REQUIRED, "Illegal Content-Length:" + contentLength);
//            }
//            if (requestLarge > 0 && contentLength > requestLarge) {
//                throw new HttpErrorException(HttpStatusCodes.REQUEST_TOO_LARGE, "request large:" + contentLength + ", max support:" + requestLarge);
//            }
//        } catch (Exception e) {
//            throw new HttpErrorException(HttpStatusCodes.BAD_REQUEST, e.getMessage());
//        }
    }
}
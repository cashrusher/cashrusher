package com.rusher.ws;


import com.rusher.exception.HttpErrorException;
import com.rusher.exception.MessageUnmarshalException;
import com.rusher.http.HttpHeaderHelper;
import com.rusher.http.HttpStatusCodes;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

public class WebServiceRequestReplyWriter {
    public void write(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Charset charset, String response) {
        try {
            final String contentType = HttpHeaderHelper.getContentType(httpServletRequest);
            if (HttpHeaderHelper.isAcceptGZip(httpServletRequest)) {
                HttpResponseHelper.writeGZip(response, contentType, charset, httpServletResponse);
            } else {
                HttpResponseHelper.write(response, contentType, charset, httpServletResponse);
            }
        } catch (Exception | Error e) {
            throw new RuntimeException(e);
        }
    }

    public void writeError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Charset charset, Throwable e) {
        if  (e instanceof MessageUnmarshalException) {
            sendHttpError(httpServletResponse, HttpStatusCodes.BAD_REQUEST, "Illegal message format");
        }else if (e instanceof NotFoundException) {
            sendHttpError(httpServletResponse, HttpStatusCodes.NOT_FOUND, e.getMessage());
        } else if (e instanceof HttpErrorException) {
            sendHttpError(httpServletResponse, ((HttpErrorException) e).getCode(), e.getMessage());
        } else {
            sendHttpError(httpServletResponse, HttpStatusCodes.INTERNAL_SERVER_ERROR, e.getClass().getName() + ":" + e.getMessage());
        }
    }

    private void sendHttpError(HttpServletResponse httpServletResponse, int errorCode, String errorMessage) {
        try {
            httpServletResponse.sendError(errorCode, errorMessage);
        } catch (Exception | Error e) {
            throw new RuntimeException(e);
        }
    }
}
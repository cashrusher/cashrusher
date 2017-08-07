package com.rusher.domain.common.exception;

public class WebServiceAccessException extends RuntimeException {
    public WebServiceAccessException(String message) {
        super(message, null);
    }

    public WebServiceAccessException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}

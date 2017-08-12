package com.rusher.common.exception;

public class HttpErrorException extends RuntimeException {
    private final int code;

    public HttpErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + code + ":" + getMessage();
    }
}

package com.rusher.domain.common.exception;

public class MessageUnmarshalException extends RuntimeException {
    public MessageUnmarshalException(String message) {
        super(message, null);
    }

    public MessageUnmarshalException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}

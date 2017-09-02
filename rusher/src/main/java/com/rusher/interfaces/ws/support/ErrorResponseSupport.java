package com.rusher.interfaces.ws.support;

import com.rusher.interfaces.dto.Error;
import com.rusher.interfaces.dto.ErrorResponse;

import static com.rusher.interfaces.dto.Status.Failed;

/**
 * Created by liam on 02/09/2017.
 */
public class ErrorResponseSupport {
    public static ErrorResponse create(String code, String message) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(Failed);
        response.setError(createError(code, message));
        return response;
    }

    private static Error createError(String code, String message) {
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }
}

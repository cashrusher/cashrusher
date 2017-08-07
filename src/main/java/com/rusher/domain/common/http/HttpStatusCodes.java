package com.rusher.domain.common.http;

public interface HttpStatusCodes {
    int SUCCESS = 200;
    int BAD_REQUEST = 400;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int CONTENT_LENGTH_REQUIRED = 411;
    int REQUEST_TOO_LARGE = 413;
    int INTERNAL_SERVER_ERROR = 500;
}

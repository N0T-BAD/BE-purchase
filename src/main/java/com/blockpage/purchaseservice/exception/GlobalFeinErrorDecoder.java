package com.blockpage.purchaseservice.exception;

import static com.blockpage.purchaseservice.exception.ErrorCode.*;

import feign.Response;
import feign.codec.ErrorDecoder;

public class GlobalFeinErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (400 <= response.status() && response.status() < 500) {
            return new BusinessException(BLOCK_SERVICE_CLIENT_BAD_REQUEST.getMessage(), BLOCK_SERVICE_CLIENT_BAD_REQUEST.getHttpStatus());
        } else if (500 <= response.status() && response.status() < 600) {
            return new BusinessException(BLOCK_SERVICE_SERVER_UNAVAILABLE.getMessage(), BLOCK_SERVICE_SERVER_UNAVAILABLE.getHttpStatus());
        }
        return new BusinessException(UNKNOWN_ERROR.getMessage(), UNKNOWN_ERROR.getHttpStatus());
    }
}
package com.lessmatch.lessmatch.api.error;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
    
}

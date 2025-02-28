package com.lessmatch.lessmatch.api.error;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message) {
        super(message);
    }
}

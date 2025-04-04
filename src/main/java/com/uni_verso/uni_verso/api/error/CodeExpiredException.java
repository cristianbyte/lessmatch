package com.uni_verso.uni_verso.api.error;

public class CodeExpiredException extends RuntimeException {
    public CodeExpiredException(String message) {
        super(message);
    }    
}

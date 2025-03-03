package com.lessmatch.lessmatch.api.error;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
}

package com.lessmatch.lessmatch.api.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
    private Map<String, String> errors;
}

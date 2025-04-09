package com.uni_verso.uni_verso.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanListConverter implements AttributeConverter<List<Boolean>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String convertToDatabaseColumn(List<Boolean> attribute) {
        try {
            if (attribute == null) {
                return null;
            }
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting list to JSON", e);
        }
    }
    
    @Override
    public List<Boolean> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(dbData, 
                    new TypeReference<List<Boolean>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to list", e);
        }
    }
}
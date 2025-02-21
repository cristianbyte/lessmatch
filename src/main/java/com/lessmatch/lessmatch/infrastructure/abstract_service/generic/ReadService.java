package com.lessmatch.lessmatch.infrastructure.abstract_service.generic;

public interface ReadService<Response, Id> {
    Response getById(Id id);    
}

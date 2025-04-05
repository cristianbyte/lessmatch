package com.uni_verso.uni_verso.infrastructure.abstract_service.generic;

public interface ReadService<Response, Id> {
    Response getById(Id id);    
}

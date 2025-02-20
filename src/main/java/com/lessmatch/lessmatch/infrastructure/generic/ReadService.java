package com.lessmatch.lessmatch.infrastructure.generic;

import java.util.Optional;

public interface ReadService<Response, Id> {
    Optional<Response> getById(Id id);    
}

package com.uni_verso.uni_verso.infrastructure.abstract_service.generic;

public interface CreateService<Request, Response> {
    Response create(Request request);
}

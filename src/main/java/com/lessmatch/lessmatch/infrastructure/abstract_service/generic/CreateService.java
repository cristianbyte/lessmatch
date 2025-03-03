package com.lessmatch.lessmatch.infrastructure.abstract_service.generic;

public interface CreateService<Request, Response> {
    Response create(Request request);
}

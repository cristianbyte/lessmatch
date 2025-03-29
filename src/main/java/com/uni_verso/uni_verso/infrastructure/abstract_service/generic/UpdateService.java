package com.uni_verso.uni_verso.infrastructure.abstract_service.generic;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request);
}

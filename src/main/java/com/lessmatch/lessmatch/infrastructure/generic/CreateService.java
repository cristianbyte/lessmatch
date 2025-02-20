package com.lessmatch.lessmatch.infrastructure.generic;

public interface CreateService<Request, Response> {
    Response create(Request request);
}

package com.lessmatch.lessmatch.infrastructure.generic;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request);
}

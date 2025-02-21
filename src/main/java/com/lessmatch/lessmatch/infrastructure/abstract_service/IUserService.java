package com.lessmatch.lessmatch.infrastructure.abstract_service;

import com.lessmatch.lessmatch.api.dto.request.UserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;

public interface IUserService extends
    CrudService<UserRequest, UserResponse, String>
{
}

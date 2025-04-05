package com.uni_verso.uni_verso.infrastructure.abstract_service;

import com.uni_verso.uni_verso.api.dto.request.UserRequest;
import com.uni_verso.uni_verso.api.dto.response.UserResponse;

public interface IUserService extends
    CrudService<UserRequest, UserResponse, String>
{
}

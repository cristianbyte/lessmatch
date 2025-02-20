package com.lessmatch.lessmatch.infrastructure;

import com.lessmatch.lessmatch.api.dto.request.CreateUserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;

public interface IUserService extends
    CrudService<CreateUserRequest, UserResponse, Long>
{
    UserResponse findByUserIdentifier(String userIdentifier);
}

package com.lessmatch.lessmatch.infrastructure;

import java.util.List;

import com.lessmatch.lessmatch.api.dto.request.UserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;
import com.lessmatch.lessmatch.infrastructure.generic.CreateService;
import com.lessmatch.lessmatch.infrastructure.generic.DeleteService;
import com.lessmatch.lessmatch.infrastructure.generic.ReadService;
import com.lessmatch.lessmatch.infrastructure.generic.UpdateService;

public interface IUserService extends
    CreateService<UserRequest, UserResponse>,
    ReadService<UserResponse, Long>,
    UpdateService<UserRequest, UserResponse, Long>,
    DeleteService<Long>
{
    List<UserResponse> findAll();
}

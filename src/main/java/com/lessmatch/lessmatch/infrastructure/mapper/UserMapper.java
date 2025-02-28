package com.lessmatch.lessmatch.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.lessmatch.lessmatch.api.dto.request.UserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;
import com.lessmatch.lessmatch.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdPairings", ignore = true)
    @Mapping(target = "joinedPairings", ignore = true)
    User toEntity(UserRequest userRequest);
    
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdPairings", ignore = true)
    @Mapping(target = "joinedPairings", ignore = true)
    void toUpdate(UserRequest user, @MappingTarget User target);

}
package com.uni_verso.uni_verso.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.uni_verso.uni_verso.api.dto.request.UserRequest;
import com.uni_verso.uni_verso.api.dto.response.UserResponse;
import com.uni_verso.uni_verso.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdPairings", ignore = true)
    @Mapping(target = "joinedPairings", ignore = true)
    User toEntity(UserRequest userRequest);
    
    @Mapping(target = "pairings", ignore = true)
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdPairings", ignore = true)
    @Mapping(target = "joinedPairings", ignore = true)
    void toUpdate(UserRequest user, @MappingTarget User target);

}
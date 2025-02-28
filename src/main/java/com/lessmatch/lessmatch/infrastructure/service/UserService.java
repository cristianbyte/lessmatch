package com.lessmatch.lessmatch.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lessmatch.lessmatch.api.dto.request.UserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;
import com.lessmatch.lessmatch.api.error.IdNotFoundException;
import com.lessmatch.lessmatch.api.error.InvalidOperationException;
import com.lessmatch.lessmatch.domain.entity.User;
import com.lessmatch.lessmatch.domain.repo.UserRepo;
import com.lessmatch.lessmatch.infrastructure.abstract_service.IUserService;
import com.lessmatch.lessmatch.infrastructure.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepo userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {
        // Pending: Verify the user via email.
        if(userRepository.findById(request.getId()).isPresent()){
            throw new InvalidOperationException("User already exists with id: " + request.getId());
        }

        User user = userMapper.toEntity(request);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(String id) {
        return userMapper.toResponse(this.find(id));
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        User user = find(id);
        userMapper.toUpdate(request, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void delete(String id) {
        userRepository.delete(this.find(id));
    }


    public User find(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("User not found with id: " + id));
    }
}

package com.lessmatch.lessmatch.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessmatch.lessmatch.api.dto.request.UserRequest;
import com.lessmatch.lessmatch.api.dto.response.UserResponse;
import com.lessmatch.lessmatch.infrastructure.abstract_service.IUserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable String id){
        return ResponseEntity.ok( this.userService.getById(id));
    }
    
}

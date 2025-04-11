package com.uni_verso.uni_verso.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni_verso.uni_verso.api.dto.request.UserRequest;
import com.uni_verso.uni_verso.api.dto.response.UserResponse;
import com.uni_verso.uni_verso.infrastructure.abstract_service.IUserService;
import com.uni_verso.uni_verso.util.JwtUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(
    origins = {"https://uni-verso.vercel.app", "https://universo.coder.red"}, 
    allowCredentials = "true", 
    allowedHeaders = {"Authorization", "Content-Type", "Accept"}
)
public class UserController {

    private final IUserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> create(@Validated @RequestBody UserRequest request) {
        UserResponse userResponse = this.userService.create(request);
        String token = jwtUtils.createToken(userResponse);

        Map<String, Object> response = new HashMap<>();
        response.put("user", userResponse);
        response.put("token", token);
        System.out.println("Generated token: " + token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable String id) {
        UserResponse userResponse = this.userService.getById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Validated @RequestBody UserRequest request) {
        UserResponse userResponse = this.userService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

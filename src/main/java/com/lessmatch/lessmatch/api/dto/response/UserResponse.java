package com.lessmatch.lessmatch.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    
    private Long id;
    private String name;
    private String lastName;
    private String icon;
    private String createdAt;
}

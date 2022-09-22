package com.example.assignment5.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final boolean isEnabled;
    private final List<RoleDto> roles;

    @Data
    public static class RoleDto implements Serializable {
        private final Long id;
        private final String name;
    }
}
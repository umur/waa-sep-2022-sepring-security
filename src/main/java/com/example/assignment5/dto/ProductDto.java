package com.example.assignment5.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long id;
    private final String name;
    private final int price;
    private final UserDto user;
    private final CategoryDto category;

    @Data
    public static class UserDto implements Serializable {
        private final Long id;
        private final String username;
        private final String password;
        private final String email;
        private final boolean isEnabled;
    }

    @Data
    public static class CategoryDto implements Serializable {
        private final Long id;
        private final String name;
    }
}
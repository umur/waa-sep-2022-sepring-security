package com.springsecuritydata1.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springsecuritydata1.springsecurity.entity.User;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;

    private String name;
    private Double price;
    private Integer rating;
    private User user;
    @JsonBackReference
    private CategoryDTO categoryDTO;
}

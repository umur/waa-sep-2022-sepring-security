package com.springsecuritydata1.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springsecuritydata1.springsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Integer id;
    private String comment;

    @JsonBackReference
    private User user;
}


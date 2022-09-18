package edu.miu.lab5springsecurity.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String comment;
    private int numberOfStars;
}

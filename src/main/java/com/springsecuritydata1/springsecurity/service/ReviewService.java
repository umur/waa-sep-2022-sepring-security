package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO SaveReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReview();

    ReviewDTO getReviewById(Integer id);

    ReviewDTO updateReview(ReviewDTO reviewDTO, Integer id);
}

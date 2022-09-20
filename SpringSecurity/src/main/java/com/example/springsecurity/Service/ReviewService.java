package com.example.springsecurity.Service;



import com.example.springsecurity.DTO.ReviewDto;
import com.example.springsecurity.Model.Review;

import java.util.List;

public interface ReviewService {
    public List<ReviewDto> findAllReview();
    public ReviewDto findReviewByID();
    public void addReview();
    public void updateReview();
    public void deleteReview();
}

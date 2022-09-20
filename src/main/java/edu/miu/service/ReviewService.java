package edu.miu.service;


import edu.miu.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    Review findReviewById(int id);
    void deleteReviewById(int id);
    void saveReview(Review review);

}

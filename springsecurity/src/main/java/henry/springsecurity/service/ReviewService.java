package henry.springsecurity.service;

import henry.springsecurity.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void save(ReviewDto reviewDto);
    List<ReviewDto> findAll();
    void update(int reviewId, ReviewDto reviewDto);
    ReviewDto delete(int reviewId);
}

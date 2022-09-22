package henry.springsecurity.service.impl;

import henry.springsecurity.dto.ReviewDto;
import henry.springsecurity.entity.Review;
import henry.springsecurity.mapper.ListMapper;
import henry.springsecurity.repo.ReviewRepo;
import henry.springsecurity.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;
    @Override
    public void save(ReviewDto reviewDto) {
        reviewRepo.save(modelMapper.map(reviewDto, Review.class));
    }

    @Override
    public List<ReviewDto> findAll() {
        return listMapper.map((List<Review>)reviewRepo.findAll(), ReviewDto.class);
    }

    @Override
    public void update(int reviewId, ReviewDto reviewDto) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null) {
            Review newReview = modelMapper.map(reviewDto, Review.class);
            newReview.setId(reviewId);
            reviewRepo.save(newReview);
        }
    }

    @Override
    public ReviewDto delete(int reviewId) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        reviewRepo.deleteById(reviewId);
        return review != null ? modelMapper.map(review, ReviewDto.class) : null;
    }
}

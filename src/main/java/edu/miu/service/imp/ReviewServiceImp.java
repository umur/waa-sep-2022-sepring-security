package edu.miu.service.imp;


import edu.miu.entity.Review;
import edu.miu.repo.ReviewRepo;
import edu.miu.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Review> findAll() {
        return mapIterableToReview(reviewRepo.findAll());
    }

    private List<Review> mapIterableToReview(Iterable<Review> reviewIterable) {
        List<Review> reviews = new ArrayList<>();
        for(Review r : reviewIterable) {
            reviews.add(modelMapper.map(r, Review.class));
        }
        return reviews;
    }
    @Override
    public Review findReviewById(int id) {
        return modelMapper.map(reviewRepo.findById(id), Review.class) ;
    }

    @Override
    public void deleteReviewById(int id) {
        reviewRepo.deleteById(id);
    }

    @Override
    public void saveReview(Review review) {
        reviewRepo.save(review);
    }
}

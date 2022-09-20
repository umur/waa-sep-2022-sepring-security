package edu.miu.controller;


import edu.miu.entity.Review;
import edu.miu.service.imp.ReviewServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewServiceImp reviewService;

    @GetMapping
    public List<Review> findAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public Review findReviewById( @PathVariable int id) {
        return reviewService.findReviewById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable int id) {
        reviewService.deleteReviewById(id);
    }

    @PostMapping
    public void saveReview(@RequestBody Review review) {
        reviewService.saveReview(review);
    }
}

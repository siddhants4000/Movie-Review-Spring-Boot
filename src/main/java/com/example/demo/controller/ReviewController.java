package com.example.demo.controller;

import com.example.demo.entity.Review;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.ReviewRequest;
import com.example.demo.response.ReviewResponse;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/add")
    public WrapperResponse<ReviewResponse> addMovie(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }

    @GetMapping("/movie-title")
    public WrapperResponse<List<Review>> getReviewByTitle(@RequestParam String title) {
        return reviewService.getReviewByTitle(title);
    }

    @GetMapping("/userId")
    public WrapperResponse<List<Review>> getReviewByUserId(@RequestParam String userId) {
        return reviewService.getReviewByUserId(userId);
    }
}

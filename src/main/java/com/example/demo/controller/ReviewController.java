package com.example.demo.controller;

import com.example.demo.model.WrapperResponse;
import com.example.demo.request.ReviewRequest;
import com.example.demo.response.ReviewResponse;
import com.example.demo.service.MovieService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/add")
    public WrapperResponse<ReviewResponse> addMovie(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }
}

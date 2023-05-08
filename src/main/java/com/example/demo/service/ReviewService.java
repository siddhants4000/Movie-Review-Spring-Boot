package com.example.demo.service;

import com.example.demo.entity.Review;
import com.example.demo.enums.StatusCode;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.MovieRepository;
import com.example.demo.repo.ReviewRepository;
import com.example.demo.request.ReviewRequest;
import com.example.demo.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieRepository movieRepository;

    public WrapperResponse<ReviewResponse> addReview(ReviewRequest reviewRequest) {
        if (Objects.isNull(movieRepository.findByTitle(reviewRequest.getMovieTitle()))) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Movie does not exists.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<ReviewResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            Review newReview = Review.builder()
                    .movieTitle(reviewRequest.getMovieTitle())
                    .rating(reviewRequest.getRating())
                    .review(reviewRequest.getReview())
                    .userId(reviewRequest.getUserId())
                    .createdAt(LocalDateTime.now())
                    .build();

            Status resultStatus = Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Review has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            reviewRepository.save(newReview);

            return WrapperResponse.<ReviewResponse>builder()
                    .data(
                            ReviewResponse.builder()
                                    .id(newReview.getId())
                                    .movieTitle(newReview.getMovieTitle())
                                    .rating(newReview.getReview())
                                    .review(newReview.getReview())
                                    .userId(newReview.getUserId())
                                    .createdAt(newReview.getCreatedAt())
                                    .build()
                    )
                    .status(resultStatus)
                    .build();
        }
    }
}

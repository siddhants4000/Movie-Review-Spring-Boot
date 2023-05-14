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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Value("${sendMailTo}")
    private String emailTo;

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

            String subject= "Review Added Successfully.";

            String body= "Review Details are- \n "+ newReview;

            emailSenderService.sendEmail(emailTo, subject, body);

            return WrapperResponse.<ReviewResponse>builder()
                    .data(
                            ReviewResponse.builder()
                                    .id(newReview.getId())
                                    .movieTitle(newReview.getMovieTitle())
                                    .rating(newReview.getRating())
                                    .review(newReview.getReview())
                                    .userId(newReview.getUserId())
                                    .createdAt(newReview.getCreatedAt())
                                    .build()
                    )
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<List<Review>> getReviewByTitle(String title) {
        if (Objects.isNull(movieRepository.findByTitle(title))) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Movie does not exists.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<List<Review>>builder()
                    .status(resultStatus)
                    .build();
        }else {
            List<Review> reviews=reviewRepository.findByMovieTitle(title);
            Status resultStatus = Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Review has been found successfully.")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<List<Review>>builder()
                    .data(reviews)
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<List<Review>> getReviewByUserId(String userId) {
        List<Review> reviews=reviewRepository.findByUserId(userId);
        Status resultStatus = Status.builder()
                .code(StatusCode.SUCCESS.getCode())
                .message("Reviews have been found successfully.")
                .success(Boolean.TRUE)
                .build();
        return WrapperResponse.<List<Review>>builder()
                .data(reviews)
                .status(resultStatus)
                .build();
    }

    public Double movieRating(String title) {
        Double ratings= reviewRepository.getReviewAverage(title);
        if(Objects.isNull(ratings))
        {
            return 0.0;
        }
        return ratings;
    }

    public WrapperResponse<ReviewResponse> deleteReview(String title, String userId) {
        if(Objects.isNull(reviewRepository.findByMovieTitleAndUserId(title, userId))) {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Review does not exists")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<ReviewResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            int reviewId= reviewRepository.findByMovieTitleAndUserId(title, userId).getId();
            reviewRepository.deleteById(reviewId);
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Review has been deleted.")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<ReviewResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }
}

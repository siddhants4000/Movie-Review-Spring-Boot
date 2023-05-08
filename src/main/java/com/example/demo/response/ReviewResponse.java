package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewResponse {

    private int id;

    @JsonProperty("movieTitle")
    private String movieTitle;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("review")
    private String review;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
}

package com.example.demo.request;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    @NotNull
    private String movieTitle;

    @NotNull
    private Double rating;

    @NotNull
    private String review;

    @NotNull
    private String userId;

    private LocalDateTime createdAt;
}

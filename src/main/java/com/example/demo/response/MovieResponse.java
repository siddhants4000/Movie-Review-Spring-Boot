package com.example.demo.response;

import com.example.demo.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieResponse {

    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("rating")
    private Double rating=0.0;

    @JsonProperty("reviews")
    private List<Review> reviews;

    @JsonProperty("releaseDate")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate releaseDate;

    @JsonProperty("length")
    private Integer length;
}

package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

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
    private String rating;

    @JsonProperty("reviews")
    private String reviews;

    @JsonProperty("releaseDate")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate releaseDate;

    @JsonProperty("length")
    private Integer length;
}

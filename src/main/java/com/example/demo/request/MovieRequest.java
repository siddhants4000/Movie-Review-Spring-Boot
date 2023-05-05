package com.example.demo.request;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

    @NotNull
    private String title;

    @NotNull
    private String genre;

    @NotNull
    private String rating;

    @NotNull
    private String reviews;

    @Nullable
    private LocalDate releaseDate;

    @NotNull
    private Integer length;
}

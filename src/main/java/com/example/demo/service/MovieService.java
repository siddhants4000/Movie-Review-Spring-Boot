package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import com.example.demo.enums.StatusCode;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.MovieRepository;
import com.example.demo.repo.ReviewRepository;
import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    EmailSenderService emailSenderService;

    @Value("${sendMailTo}")
    private String emailTo;

    public WrapperResponse<MovieResponse> addMovie(MovieRequest movieRequest) {
        if(Objects.nonNull(movieRepository.findByTitle(movieRequest.getTitle()))){
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Movie already exists")
                    .success(Boolean.TRUE)
                    .build();
            return WrapperResponse.<MovieResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            Movie newMovie=Movie.builder()
                    .title(movieRequest.getTitle())
                    .genre(movieRequest.getGenre())
                    .rating(0.0)
                    .releaseDate(LocalDate.now())
                    .length(movieRequest.getLength())
                    .build();

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Movie has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            movieRepository.save(newMovie);

            String subject= "Movie Added Successfully.";

            String body= "Movie Details are- \n "+ newMovie;

            emailSenderService.sendEmail(emailTo, subject, body);

            return WrapperResponse.<MovieResponse>builder()
                    .data(
                            MovieResponse.builder()
                                    .id(newMovie.getId())
                                    .title(newMovie.getTitle())
                                    .genre(newMovie.getGenre())
                                    .rating(newMovie.getRating())
                                    .reviews(null)
                                    .releaseDate(newMovie.getReleaseDate())
                                    .length(newMovie.getLength())
                                    .build()
                    )
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<MovieResponse> deleteMovie(String title) {
        if (Objects.isNull(movieRepository.findByTitle(title))){
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Movie does not exists")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<MovieResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            int movieId= movieRepository.findByTitle(title).getId();

            String subject= "Movie Has Been Deleted Successfully.";

            String body= "Movie Details are- \n "+ movieRepository.findById(movieId);

            emailSenderService.sendEmail(emailTo, subject, body);

            movieRepository.deleteById(movieId);
            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Movie has been deleted.")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<MovieResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }

    public List<Movie> getAllMovies() {
        for(int i=1;i<=movieRepository.count();i++){
            Optional<Movie> movie=movieRepository.findById(i);
            movie.get().setRating(reviewService.movieRating(movie.get().getTitle()));
        }
        return (List<Movie>) movieRepository.findAll();
    }

    public WrapperResponse<MovieResponse> searchMovie(String title) {
        if (Objects.isNull(movieRepository.findByTitle(title))){
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("Movie does not exists")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<MovieResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            Movie findMovie= movieRepository.findByTitle(title);

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Movie has been found.")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<MovieResponse>builder()
                    .data(MovieResponse.builder()
                            .id(findMovie.getId())
                            .title(findMovie.getTitle())
                            .genre(findMovie.getGenre())
                            .rating(reviewService.movieRating(findMovie.getTitle()))
                            .reviews(reviewRepository.findByMovieTitle(findMovie.getTitle()))
                            .releaseDate(findMovie.getReleaseDate())
                            .length(findMovie.getLength())
                            .build()
                    )
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<List<Movie>> searchByGenre(String genre) {
        List<Movie> movies=movieRepository.findByGenre(genre);
        Status resultStatus = Status.builder()
                .code(StatusCode.SUCCESS.getCode())
                .message("Movies have been found successfully.")
                .success(Boolean.TRUE)
                .build();
        return WrapperResponse.<List<Movie>>builder()
                .data(movies)
                .status(resultStatus)
                .build();
    }
}

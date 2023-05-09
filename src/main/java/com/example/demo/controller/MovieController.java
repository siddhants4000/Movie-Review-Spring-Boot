package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("admin/add")
    public WrapperResponse<MovieResponse> addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @PostMapping("admin/delete")
    public WrapperResponse<MovieResponse> deleteMovie(@RequestParam String title) {
        return movieService.deleteMovie(title);
    }

    @GetMapping("user/all")
    public List<Movie> allMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("user/search")
    public WrapperResponse<MovieResponse> searchMovie(@RequestParam String title) {
        return movieService.searchMovie(title);
    }

    @GetMapping("user/searchByGenre")
    public WrapperResponse<List<Movie>> searchByGenre(@RequestParam String genre) {
        return movieService.searchByGenre(genre);
    }
}

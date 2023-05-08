package com.example.demo.repo;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>, JpaSpecificationExecutor<Review> {

    List<Review> findByMovieTitle(String movieTitle);

    List<Review> findByUserId(String userId);
}

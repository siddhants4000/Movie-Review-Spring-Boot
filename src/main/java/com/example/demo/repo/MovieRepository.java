package com.example.demo.repo;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {

    Movie findByTitle(String name);

}

package com.example.movieinfoservice.resources;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.movieinfoservice.models.Movie; 

public interface MovieRepo extends MongoRepository<Movie, String>{
    
}

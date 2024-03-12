package com.moviecatalogservice.services;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviecatalogservice.protobuf.java.TrendingMoviesServiceGrpc;
import com.moviecatalogservice.protobuf.java.Trendingmovies.TopMoviesRequest;
import com.moviecatalogservice.protobuf.java.Trendingmovies.TopMoviesResponse;
import com.moviecatalogservice.protobuf.java.Trendingmovies.MovieRating;
import com.moviecatalogservice.models.Rating;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
@Service
public class TrendingMoviesController {
    private   static TrendingMoviesServiceGrpc.TrendingMoviesServiceBlockingStub stub;
         
         TrendingMoviesController(Channel channel){
             stub =TrendingMoviesServiceGrpc.newBlockingStub(channel);

         }
    public static   List<Rating> getTopMovies(int count) {
        
       
        // Create a request message
        TopMoviesRequest request = TopMoviesRequest.newBuilder().setCount(count).build();

        // Call the gRPC service method and get the response
        List<Rating> ratings = new ArrayList<Rating>();
        TopMoviesResponse response = stub.getTopMovies(request);

        try{
        
        for (MovieRating movie : response.getRatingsList()) {
           ratings.add(new Rating(movie.getMovieId(), movie.getRating())); 
        }
        } catch (StatusRuntimeException e) {
      return ratings;
    }


        return ratings;
    }
}

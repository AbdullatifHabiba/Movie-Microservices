package com.moviecatalogservice.services;


import com.moviecatalogservice.protobuf.java.Trendingmovies.MovieRating;

import com.moviecatalogservice.protobuf.java.Trendingmovies.TopMoviesRequest;
import com.moviecatalogservice.protobuf.java.Trendingmovies.TopMoviesResponse;
import com.moviecatalogservice.protobuf.java.TrendingMoviesServiceGrpc;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrendingMoviesService extends TrendingMoviesServiceGrpc.TrendingMoviesServiceImplBase {


    @Override
    public void getTopMovies(TopMoviesRequest request, StreamObserver<TopMoviesResponse> responseObserver) {
        System.out.println("Received request for top " + request.getCount() + " movies");
        List<MovieRating> topMovies = fetchTopMoviesByRating(request.getCount());
        if (topMovies != null) {
            TopMoviesResponse response = TopMoviesResponse.newBuilder().addAllRatings(topMovies).build();
            System.out.println("Sending response...");
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

  
   

private List<MovieRating> fetchTopMoviesByRating(int count) {
      List<MovieRating> ratings = new ArrayList<>();
      Statement st =DBConnection.connect();
try (//        System.out.println("select * from Rating where userId="+"\'"+userId+"\'");
        ResultSet res = st.executeQuery("SELECT movieId, rating FROM Rating" +
        " ORDER BY rating DESC LIMIT " + count)) {
            while(res.next()){
                System.out.println(res.getString(1) + " " + res.getString(2));
            ratings.add(MovieRating.newBuilder().setMovieId(res.getString(1)).setRating(Integer.parseInt(res.getString(2))).build());
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
return ratings;
    
    
    }

}

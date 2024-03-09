package com.moviecatalogservice.services;

import com.moviecatalogservice.protobuf.java.Movie;

import com.moviecatalogservice.protobuf.java.TopMoviesRequest;
import com.moviecatalogservice.protobuf.java.TopMoviesResponse;
import com.moviecatalogservice.protobuf.java.TrendingMoviesServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import  java.util.*;

@GrpcService
public class TrendingMoviesService extends TrendingMoviesServiceGrpc.TrendingMoviesServiceImplBase {

    @Override
    public void getTopMovies(TopMoviesRequest request, StreamObserver<TopMoviesResponse> responseObserver) {
        // TODO: Implement the logic to fetch the top movies.
        // Create a response message
        System.out.println("Received request for top " + request.getCount() + " movies");
        List<Movie> topMovies = fetchTopMoviesByRating(request.getCount()); // Fetch top 10 movies

        // Convert Movie objects to TopMoviesResponse.Builder objects
        TopMoviesResponse.Builder responseBuilder = TopMoviesResponse.newBuilder();
        for (Movie movie : topMovies) {

            responseBuilder.addMovies(movie);
        }

        // Send the response
        System.out.println("Sending response...");

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private List<Movie> fetchTopMoviesByRating(int i) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8083/{i}"))
        .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return List.of(
                Movie.newBuilder().setRating(5).setName("Movie 1").setDescription("Description 1").build(),
                Movie.newBuilder().setRating(4).setName("Movie 2").setDescription("Description 2").build(),
                Movie.newBuilder().setRating(3).setName("Movie 3").setDescription("Description 3").build(),
                Movie.newBuilder().setRating(2).setName("Movie 4").setDescription("Description 4").build(),
                Movie.newBuilder().setRating(1).setName("Movie 5").setDescription("Description 5").build(),
                Movie.newBuilder().setRating(5).setName("Movie 6").setDescription("Description 6").build(),
                Movie.newBuilder().setRating(4).setName("Movie 7").setDescription("Description 7").build(),
                Movie.newBuilder().setRating(3).setName("Movie 8").setDescription("Description 8").build(),
                Movie.newBuilder().setRating(2).setName("Movie 9").setDescription("Description 9").build(),
                Movie.newBuilder().setRating(1).setName("Movie 10").setDescription("Description 10").build()

                // Add more movies here as needed
        );
    }
}

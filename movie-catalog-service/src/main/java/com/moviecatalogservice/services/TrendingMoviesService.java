package com.moviecatalogservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class TrendingMoviesService extends TrendingMoviesServiceGrpc.TrendingMoviesServiceImplBase {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public TrendingMoviesService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void getTopMovies(TopMoviesRequest request, StreamObserver<TopMoviesResponse> responseObserver) {
        System.out.println("Received request for top " + request.getCount() + " movies");
        List<Movie> topMovies = fetchTopMoviesByRating(request.getCount());
        if (topMovies != null) {
            TopMoviesResponse response = TopMoviesResponse.newBuilder().addAllMovies(topMovies).build();
            System.out.println("Sending response...");
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    private List<Movie> fetchTopMoviesByRating(int count) {
        List<Movie> movies = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8083/ratings/Top/" + count))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Response from ratings-data-service: " + response.body());
                movies = parseJsonResponse(response.body());

            } else {
                System.out.println("Error: Unexpected response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error fetching top movies: " + e.getMessage());
        }
        return movies;
    }

    private List<Movie> parseJsonResponse(String responseBody) {
        System.out.println("Parsing JSON response...");
        List<Movie> movies = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            System.out.println("JSON response: " + jsonNode);
            for (JsonNode rating : jsonNode.get("ratings")) {
                int movieId = rating.get("movieId").asInt();
                int movieRating = rating.get("rating").asInt();
                System.out.println("Movie ID: " + movieId + ", Rating: " + movieRating);

                HttpRequest movieRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8082/movies/" + movieId))
                        .build();
                HttpResponse<String> movieResponse = httpClient.send(movieRequest, HttpResponse.BodyHandlers.ofString());
                JsonNode movieJsonNode = objectMapper.readTree(movieResponse.body());
                Movie movie = Movie.newBuilder()
                        .setRating(movieRating)
                        .setName(movieJsonNode.get("name").asText())
                        .setDescription(movieJsonNode.get("description").asText())
                        .build();
                movies.add(movie);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
        }
        return movies;
    }
}

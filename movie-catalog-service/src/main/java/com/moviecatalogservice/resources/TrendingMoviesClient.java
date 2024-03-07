package com.moviecatalogservice.resources;

import com.moviecatalogservice.protobuf.java.Movie;
import com.moviecatalogservice.protobuf.java.TopMoviesRequest;
import com.moviecatalogservice.protobuf.java.TopMoviesResponse;
import com.moviecatalogservice.protobuf.java.TrendingMoviesServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class TrendingMoviesClient {

    public static void main(String[] args) {
        // Create a gRPC channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // Use plaintext communication (no encryption)
                .build();

        // Create a stub for the TrendingMoviesService
        TrendingMoviesServiceGrpc.TrendingMoviesServiceBlockingStub stub =
                TrendingMoviesServiceGrpc.newBlockingStub(channel);
        // Create a request message
        TopMoviesRequest request = TopMoviesRequest.newBuilder().setCount(10).build();

        // Call the gRPC service method and get the response
        try{
        
        TopMoviesResponse response = stub.getTopMovies(request);
        System.out.println("Response: " + response);
        // Process the response (e.g., display top movies)
        System.out.println("Top Movies:");
        for (Movie movie : response.getMoviesList()) {
            System.out.println("ID: " + movie.getRating()+ ", Name: " + movie.getName() + ", Description: " + movie.getDescription());
        }
    }
    catch(StatusRuntimeException e){
        System.out.println("Exception: "+ e.getStatus());
    };
        // Shutdown the channel
        channel.shutdown();
    }
}

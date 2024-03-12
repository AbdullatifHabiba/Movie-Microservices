package com.moviecatalogservice;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.moviecatalogservice.resources.GrpcServer;
import com.moviecatalogservice.services.TrendingMoviesService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@EnableCircuitBreaker
public class TrendingMoviesApplication {

    
  

     public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    
        

    }

}

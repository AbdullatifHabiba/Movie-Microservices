package com.moviecatalogservice.resources;

import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Movie;
import com.moviecatalogservice.models.Rating;
import com.moviecatalogservice.models.UserRating;
import com.moviecatalogservice.protobuf.java.Trendingmovies.TopMoviesResponse;
import com.moviecatalogservice.services.MovieInfoService;
import com.moviecatalogservice.services.TrendingMoviesController;
import com.moviecatalogservice.services.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.grpc.ManagedChannel;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    private final MovieInfoService movieInfoService;

    private final UserRatingService userRatingService;
    private final TrendingMoviesController trendingMoviesController;
private final ManagedChannel channel;

    public MovieCatalogResource(RestTemplate restTemplate,
                                MovieInfoService movieInfoService,
                                UserRatingService userRatingService,
                                TrendingMoviesController trendingMoviesController,
                                ManagedChannel channel

                                ) {

        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.userRatingService = userRatingService;
        this.trendingMoviesController = trendingMoviesController;
        this.channel = channel;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * @param userId
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = userRatingService.getUserRating(userId).getRatings();
        return ratings.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }
    // top rated movies
    public List<CatalogItem> getFallbackCatalog(@PathVariable String userId) {
        return Collections.singletonList(new CatalogItem("No movie", "", 0));
    }
    @RequestMapping("/topRated")
    public List<CatalogItem> getTopRated() {
      List<Rating> ratings = trendingMoviesController.getTopMovies(10);
        return ratings.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }
}

package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    private MovieRepo repo;

    public void cacheMovie(Movie movie){ 
        repo.save(movie); 
    } 
    public Movie getMovieFromCache(String id) {
        return repo.findById(id).orElse(null);
    }

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // check cache first
        Movie movie = getMovieFromCache(movieId);
        if(movie != null){
            System.out.println("movie fetched from cache");
            return movie;
        }
        // Get the movie info from TMDB
        System.out.println("movie not found in cache....");
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
        movie = new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        // caching the fetched movie
        System.out.println("caching movie");
        cacheMovie(movie);

        return movie;
    }
    @RequestMapping("/post/{movieId}")
    public Movie putMovie(@PathVariable("movieId") String movieId) {
        Movie movie = new Movie("123","titanic","ay habd");
        cacheMovie(movie);

        return movie;
    }
    @RequestMapping("/get/{movieId}")
    public Movie retMovie(@PathVariable("movieId") String movieId) {
        Movie movie = getMovieFromCache(movieId);

        return movie;
    }
}

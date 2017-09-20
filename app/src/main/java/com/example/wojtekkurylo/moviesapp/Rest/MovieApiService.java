package com.example.wojtekkurylo.moviesapp.Rest;

import com.example.wojtekkurylo.moviesapp.Model.MovieComponent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Endpoint method used to query the TMDB API
 */

public interface MovieApiService {

    // TARGET: "https://api.themoviedb.org/3/movie/top_rated?api_key=INSERT_YOUR_API_KEY"
    @GET("movie/top_rated")
    Call<MovieComponent> getTopRatedMovies(@Query("api_key") String apiKey);
    // TARGET: "https://api.themoviedb.org/3/movie/popular?api_key=INSERT_YOUR_API_KEY"
    @GET("movie/popular")
    Call<MovieComponent> getMostPopularMovies(@Query("api_key") String apiKey);


}

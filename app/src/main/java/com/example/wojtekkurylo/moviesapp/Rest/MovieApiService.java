package com.example.wojtekkurylo.moviesapp.Rest;

import com.example.wojtekkurylo.moviesapp.Model.MovieComponent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Endpoint method used to query the TMDB API
 */

public interface MovieApiService {

    // TARGET: "https://api.themoviedb.org/3/movie/{SELECTION}api_key=INSERT_YOUR_API_KEY"
    @GET("movie/{selection}")
    Call<MovieComponent> getSelectedMovies(@Path("selection") String selection, @Query("api_key") String apiKey);


}

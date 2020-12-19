package com.telenorgp.tmdbgp.Services;


import com.telenorgp.tmdbgp.Entities.DetailMovie;
import com.telenorgp.tmdbgp.Entities.WrapperMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieService {
    @GET("movie/popular")
    Call<WrapperMovie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/top_rated")
    Call<WrapperMovie> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/upcoming")
    Call<WrapperMovie> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("movie/{movie_id}")
    Call<DetailMovie> getDetailMovie(@Path("movie_id") String id, @Query("api_key") String apiKey);
}

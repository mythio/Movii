package com.mythio.movii.network;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndOmdb {

    @GET("/")
    Call<MovieOmdb> getOmdbDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);
}

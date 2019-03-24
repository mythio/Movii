package com.mythio.movii.network;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointTmdb {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieTmdb> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @GET("search/movie")
    Call<MovieResponse> getMovieSearchResults(@Query("api_key") String apiKey, @Query("query") String query);
}

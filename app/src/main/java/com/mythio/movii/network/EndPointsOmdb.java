package com.mythio.movii.network;

import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.tvShow.TvShowOmdb;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointsOmdb {

    @GET("/")
    Observable<MovieOmdb> getMovieDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);

    @GET("/")
    Observable<TvShowOmdb> getTvShowDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);
}

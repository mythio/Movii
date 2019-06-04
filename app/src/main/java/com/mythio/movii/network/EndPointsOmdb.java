package com.mythio.movii.network;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.tv_show.TvShowOmdb;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointsOmdb {

    @NonNull
    @GET("/")
    Observable<MovieOmdb> getMovieDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);

    @NonNull
    @GET("/")
    Observable<TvShowOmdb> getTvShowDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);
}

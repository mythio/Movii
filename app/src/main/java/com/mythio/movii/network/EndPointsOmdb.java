package com.mythio.movii.network;

import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.tvShow.TvShowOmdb;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointsOmdb {

    @GET("/")
    Observable<MovieOmdb> getMovieDetailOmdb(@Query("apikey") String apiKey, @Query("i") String imdbId);

    @GET("/")
    Call<TvShowOmdb> getTvShowDetailOmdb(@Query("apikey") String apiKey, @Query("i") String imdbId);
}

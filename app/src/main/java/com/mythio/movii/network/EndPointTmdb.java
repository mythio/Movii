package com.mythio.movii.network;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tv_show.TvShowResponse;
import com.mythio.movii.model.tv_show.TvShowTmdb;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointTmdb {

    @NonNull
    @GET("movie/popular")
    Single<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @NonNull
    @GET("movie/{movie_id}")
    Observable<MovieTmdb> getMovieDetail(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @NonNull
    @GET("search/movie")
    Observable<MovieResponse> getMovieSearchResults(@Query("api_key") String apiKey, @Query("query") String query);

    @NonNull
    @GET("tv/popular")
    Single<TvShowResponse> getPopularTvShows(@Query("api_key") String apiKey);

    @NonNull
    @GET("tv/{tv_show_id}")
    Observable<TvShowTmdb> getTvShowDetail(@Path("tv_show_id") int tvShowId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @NonNull
    @GET("search/tv")
    Observable<TvShowResponse> getTvShowSearchResults(@Query("api_key") String apiKey, @Query("query") String query);
}

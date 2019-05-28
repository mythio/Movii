package com.mythio.movii.network;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointTmdb {

    @GET("movie/popular")
    Single<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Observable<MovieTmdb> getMovieDetail(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @GET("search/movie")
    Single<MovieResponse> getMovieSearchResults(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("tv/popular")
    Single<TvShowResponse> getPopularTvShows(@Query("api_key") String apiKey);

    @GET("tv/{tv_show_id}")
    Observable<TvShowTmdb> getTvShowDetail(@Path("tv_show_id") int tvShowId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @GET("search/tv")
    Single<TvShowResponse> getTvShowSearchResults(@Query("api_key") String apiKey, @Query("query") String query);
}

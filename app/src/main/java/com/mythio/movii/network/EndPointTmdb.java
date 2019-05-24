package com.mythio.movii.network;

import com.mythio.movii.model.collection.CollectionResponse;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointTmdb {

    @GET("movie/popular")
    Single<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieTmdb> getMovieDetail(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @GET("search/movie")
    Call<MovieResponse> getMovieSearchResults(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("collection/{collection_id}")
    Call<CollectionResponse> getCollectionResults(@Path("collection_id") String collectionId, @Query("api_key") String apiKey);

    @GET("tv/popular")
    Single<TvShowResponse> getPopularTvShows(@Query("api_key") String apiKey);

    @GET("tv/{tv_show_id}")
    Call<TvShowTmdb> getTvShowDetail(@Path("tv_show_id") int tvShowId, @Query("api_key") String apiKey, @Query("append_to_response") String parameter);

    @GET("search/tv")
    Call<TvShowResponse> getTvShowSearchResults(@Query("api_key") String apiKey, @Query("query") String query);
}

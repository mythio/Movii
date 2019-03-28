package com.mythio.movii.contract.activity.discoverActivity;

import android.util.Log;

import com.mythio.movii.contract.activity.discoverActivity.DiscoverContract.Model;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverMoviesModel implements Model.MoviesModel {

    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);

    /*
    MOVIE FRAGMENT DATA
     */

    @Override
    public void getMovies(final MoviesListener moviesListener) {

        Call<MovieResponse> call = apiServiceTmdb.getPopularMovies(API_KEY_TMDB);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                moviesListener.onFinishedMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                moviesListener.onFailureMovies(t);
                Log.v("TAG_TAG", t.getLocalizedMessage());
            }
        });
    }
}

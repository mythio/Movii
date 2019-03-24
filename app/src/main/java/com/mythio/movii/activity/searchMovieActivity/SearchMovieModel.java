package com.mythio.movii.activity.searchMovieActivity;

import android.util.Log;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class SearchMovieModel implements SearchMovieContract.Model {

    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);

    @Override
    public void getSearchResults(final OnMoviesSearchListener listener, String query) {

        Call<MovieResponse> call = apiServiceTmdb.getMovieSearchResults(API_KEY_TMDB, query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.v("TAG_TAG", response.toString());
                if (response != null) {
                    listener.onFinished(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}

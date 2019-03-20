package com.mythio.movii.contract.MovieFragment;

import android.util.Log;

import com.mythio.movii.model.Movie;
import com.mythio.movii.model.MovieResponse;
import com.mythio.movii.network.ApiClient;
import com.mythio.movii.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    @Override
    public void getData(final OnFinishedListener onFinishedListener) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovies("2e901364c3d103dcb00ced520e9bca3c");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d("TAG_TAG_TAG", "Number of movies received: " + movies.size());
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                onFinishedListener.onFailure(t);
            }
        });
    }
}
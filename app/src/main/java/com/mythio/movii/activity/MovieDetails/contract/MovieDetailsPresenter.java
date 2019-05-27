package com.mythio.movii.activity.MovieDetails.contract;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private static final String TAG = "movii.debug: MovieDetailsPresenter";

    private MovieDetailsContract.View view;
    private MovieDetailsModel model;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.view = view;
        model = new MovieDetailsModel();
    }

    @Override
    public void getDetails(String id) {
        model.getDetails(new MovieDetailsContract.Model.MovieDetailsListener() {
            @Override
            public void onFinished(Movie movie) {
                view.showDetails(movie);
            }

            @Override
            public void onFailure(String message) {
                Log.d(TAG, message);
            }
        }, Integer.valueOf(id));
    }
}
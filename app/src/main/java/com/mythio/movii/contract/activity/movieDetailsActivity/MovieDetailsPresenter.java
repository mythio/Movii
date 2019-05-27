package com.mythio.movii.contract.activity.movieDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter,
        MovieDetailsContract.Model.MovieDetailsListener {

    private MovieDetailsContract.View view;
    private MovieDetailsModel model;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.view = view;
        model = new MovieDetailsModel();
    }

    @Override
    public void getDetails(String id) {
        model.getDetails(this, Integer.valueOf(id));
    }

    @Override
    public void onFinished(Movie movie) {
        view.showDetails(movie);
    }

    @Override
    public void onFailure(String message) {
        Log.v("TAG_TAG", message);
    }
}
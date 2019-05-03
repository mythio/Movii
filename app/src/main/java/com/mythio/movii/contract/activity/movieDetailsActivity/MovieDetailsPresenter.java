package com.mythio.movii.contract.activity.movieDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnCollectionListener {

    private MovieDetailsContract.View view;
    private MovieDetailsModel model;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.view = view;
        model = new MovieDetailsModel();
    }

    @Override
    public void onRequestCollection(String id) {
        model.getDetails(this, Integer.valueOf(id));
    }

    @Override
    public void onFinished(ArrayList<Movie> movies) {
        view.showPages(movies);
    }

    @Override
    public void onFailure(String message) {
        Log.v("TAG_TAG", message);
    }
}
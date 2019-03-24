package com.mythio.movii.activity.searchMovieActivity;

import android.util.Log;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class SearchMoviePresenter implements SearchMovieContract.Presenter, SearchMovieContract.Model.OnMoviesSearchListener {

    private SearchMovieContract.View view;
    private SearchMovieContract.Model model;

    SearchMoviePresenter(SearchMovieContract.View view) {
        this.view = view;
        model = new SearchMovieModel();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(this, string);
    }

    @Override
    public void onFinished(ArrayList<MovieTmdb> movies) {
        view.showRes(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.v("TAG_TAG", "123123");
    }
}

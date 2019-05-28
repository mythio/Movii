package com.mythio.movii.activity.MovieDetails.contract;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private final MovieDetailsContract.View view;
    private final MovieDetailsModel model;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.view = view;
        model = new MovieDetailsModel();
    }

    @Override
    public void getDetails(String id) {
        model.getDetails(view::showDetails, Integer.valueOf(id));
    }
}
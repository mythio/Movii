package com.mythio.movii.activity.MovieDetails.contract;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private MovieDetailsContract.View view;
    private MovieDetailsModel model;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.view = view;
        model = new MovieDetailsModel();
    }

    @Override
    public void getDetails(String id) {
        model.getDetails(movie -> view.showDetails(movie), Integer.valueOf(id));
    }
}
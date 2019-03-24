package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View view;

    MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void initViews() {
        view.initViewPager();
    }

    @Override
    public void setDataToViewPager(ArrayList<Movie> movies) {
        view.showSlideShow(movies);
    }
}

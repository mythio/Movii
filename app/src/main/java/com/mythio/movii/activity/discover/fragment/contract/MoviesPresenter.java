package com.mythio.movii.activity.discover.fragment.contract;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class MoviesPresenter implements MoviesContract.Presenter {

    private final MoviesContract.View view;

    public MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void initViews() {
        view.initViewPager();
    }

    @Override
    public void setDataToViewPager(ArrayList<MovieTmdb> movies) {
        view.showSlideShow(movies);
    }
}

package com.mythio.movii.activity.Discover.activity.contract;

import com.mythio.movii.activity.Discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.Discover.fragment.contract.DiscoverFragmentNavigation;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class DiscoverPresenter implements DiscoverContract.Presenter, DiscoverFragmentNavigation.Presenter,
        DiscoverContract.Model.MoviesModel.MoviesListener, DiscoverContract.Model.TvShowsModel.TvShowsListener {

    private DiscoverContract.View view;
    private DiscoverContract.Model.MoviesModel moviesModel;
    private DiscoverContract.Model.TvShowsModel tvShowsModel;

    public DiscoverPresenter(DiscoverContract.View view) {
        this.view = view;
        moviesModel = new DiscoverMoviesModel();
        tvShowsModel = new DiscoverTvShowsModel();
    }

    @Override
    public void setFragment(BaseDiscoverFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void getData() {
        moviesModel.getMovies(this);
        tvShowsModel.getTvShows(this);
    }

    @Override
    public void onFinishedMovies(ArrayList<MovieTmdb> movies) {
        view.sendToMoviesFragment(movies);
    }

    @Override
    public void onFinishedTvShows(ArrayList<TvShowTmdb> tvShows) {
        view.sendToTvShowsFragment(tvShows);
    }
}

package com.mythio.movii.contract.activity.discoverActivity;

import com.mythio.movii.contract.fragment.discover.baseFragmentDiscover.DiscoverFragmentNavigation;
import com.mythio.movii.fragment.discover.BaseDiscoverFragment;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class DiscoverPresenter implements DiscoverContract.Presenter, DiscoverFragmentNavigation.Presenter, DiscoverContract.Model.MoviesModel.MoviesListener, DiscoverContract.Model.TvShowsModel.TvShowsListener {

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
    public void onDataRequest() {
        moviesModel.getMovies(this);
        tvShowsModel.getTvShows(this);
    }

    @Override
    public void onFinishedMovies(ArrayList<MovieTmdb> movies) {
        view.sendToFragmentMovies(movies);
    }

    @Override
    public void onFailureMovies(String message) {

    }

    @Override
    public void onFinishedTvShows(ArrayList<TvShowTmdb> tvShows) {
        view.sendToFragmentTvShows(tvShows);
    }

    @Override
    public void onFailureTvShows(String message) {

    }
}

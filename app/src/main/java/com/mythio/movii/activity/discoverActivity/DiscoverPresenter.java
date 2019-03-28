package com.mythio.movii.activity.discoverActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class DiscoverPresenter implements DiscoverContract.Presenter, FragmentNavigation.Presenter, DiscoverContract.Model.MoviesModel.MoviesListener, DiscoverContract.Model.TvShowsModel.TvShowsListener {

    private DiscoverContract.View view;
    private DiscoverContract.Model.MoviesModel moviesModel;
    private DiscoverContract.Model.TvShowsModel tvShowsModel;

    public DiscoverPresenter(DiscoverContract.View view) {
        this.view = view;
        moviesModel = new DiscoverMoviesModel();
        tvShowsModel = new DiscoverTvShowsModel();
    }

    @Override
    public void setFragment(BaseFragment fragment) {
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
    public void onFailureMovies(Throwable throwable) {

    }

    @Override
    public void onFinishedTvShows(ArrayList<TvShowTmdb> tvShows) {
        view.sendToFragmentTvShows(tvShows);
    }

    @Override
    public void onFailureTvShows(Throwable throwable) {

    }
}

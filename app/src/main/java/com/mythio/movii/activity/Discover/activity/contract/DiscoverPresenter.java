package com.mythio.movii.activity.Discover.activity.contract;

import com.mythio.movii.activity.Discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.Discover.fragment.contract.DiscoverFragmentNavigation;

public class DiscoverPresenter implements DiscoverContract.Presenter, DiscoverFragmentNavigation.Presenter {

    private final DiscoverContract.View view;
    private final DiscoverContract.Model.MoviesModel moviesModel;
    private final DiscoverContract.Model.TvShowsModel tvShowsModel;

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
        moviesModel.getMovies(movies -> view.sendToMoviesFragment(movies));
        tvShowsModel.getTvShows(tvShows -> view.sendToTvShowsFragment(tvShows));
    }
}

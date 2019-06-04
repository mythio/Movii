package com.mythio.movii.activity.discover.activity.contract;

import androidx.annotation.NonNull;

import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.discover.fragment.contract.FragmentNavigation;

public class Presenter implements Contract.Presenter, FragmentNavigation {

    private final Contract.View view;
    @NonNull
    private final Contract.Model.MoviesModel moviesModel;
    @NonNull
    private final Contract.Model.TvShowsModel tvShowsModel;

    public Presenter(Contract.View view) {
        this.view = view;
        moviesModel = new MoviesModel();
        tvShowsModel = new TvShowsModel();
    }

    @Override
    public void setFragment(BaseDiscoverFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void getData() {
        moviesModel.getMovies(view::sendToMoviesFragment);
        tvShowsModel.getTvShows(view::sendToTvShowsFragment);
    }
}

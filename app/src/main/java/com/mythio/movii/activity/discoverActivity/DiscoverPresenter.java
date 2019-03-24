package com.mythio.movii.activity.discoverActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class DiscoverPresenter implements DiscoverContract.Presenter, FragmentNavigation.Presenter, DiscoverContract.Model.MoviesModel.MoviesListener {

    private DiscoverContract.View view;
    private DiscoverContract.Model.MoviesModel model;

    public DiscoverPresenter(DiscoverContract.View view) {
        this.view = view;
        model = new DiscoverMoviesModel();
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void onDataRequest() {
        model.getMovies(this);
    }

    @Override
    public void onFinished(ArrayList<Movie> movies) {
        view.sendToFragment(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}

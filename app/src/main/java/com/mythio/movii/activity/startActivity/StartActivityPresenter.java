package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

public class StartActivityPresenter implements StartActivityContract.Presenter, FragmentNavigation.Presenter, StartActivityContract.Model.MoviesModel.MoviesListener {

    private StartActivityContract.View view;
    private StartActivityContract.Model.MoviesModel model;

    public StartActivityPresenter(StartActivityContract.View view) {
        this.view = view;
        model = new StartActivityMoviesModel();
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
    public void onFinished(List<Movie> movies) {
        view.sendToFragment(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}

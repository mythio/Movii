package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

public class Presenter implements Contract.Presenter, FragmentNavigation.Presenter, Contract.Model.OnDataReceived {

    private Contract.View view;
    private Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void onDataRequest() {
        model.getData(this);
    }

    @Override
    public void onFinished(List<Movie> movies) {
        view.sendToFragment(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}

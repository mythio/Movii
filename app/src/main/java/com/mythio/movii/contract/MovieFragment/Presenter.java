package com.mythio.movii.contract.MovieFragment;

import com.mythio.movii.model.Movie;

import java.util.List;

public class Presenter implements Contract.Presenter, Contract.Model.OnFinishedListener {

    private Contract.View view;
    private Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void requestData() {
        model.getData(this);
    }

    @Override
    public void onFinished(List<Movie> movies) {
        view.showText(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}

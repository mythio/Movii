package com.mythio.movii.contract.MovieFragment;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.mythio.movii.model.movie.Movie;

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
    public void initView(ViewPager viewPager) {
        view.initSlideShow(viewPager);
    }

    @Override
    public void onFinished(List<Movie> movies) {
        view.showSlideShow(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.v("TAG_TAG_ERROR", throwable.getLocalizedMessage());
    }
}

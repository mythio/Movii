package com.mythio.movii.contract.MovieFragment;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

public class MovieFragmentPresenter implements MovieFragmentContract.Presenter, MovieFragmentContract.Model.OnFinishedListener {

    private MovieFragmentContract.View view;
    private MovieFragmentContract.Model model;

    public MovieFragmentPresenter(MovieFragmentContract.View view) {
        this.view = view;
        model = new MovieFragmentModel();
    }

    @Override
    public void onDataRequest() {
        model.getData(this);
    }

    @Override
    public void onInitViewPager(ViewPager viewPager) {
        view.setSlideShow(viewPager);
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

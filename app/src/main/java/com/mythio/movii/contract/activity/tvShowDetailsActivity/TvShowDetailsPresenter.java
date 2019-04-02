package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.tvShow.TvShow;

public class TvShowDetailsPresenter implements TvShowDetailsContract.Presenter, TvShowDetailsContract.Model.OnTvShowDetailsListener {

    private TvShowDetailsContract.View view;
    private TvShowDetailsModel model;

    public TvShowDetailsPresenter(TvShowDetailsContract.View view) {
        this.view = view;
        model = new TvShowDetailsModel();
    }

    @Override
    public void onGetDetails(Integer id) {
        model.getTvShowDetails(this, id);
    }

    @Override
    public void onFinished(TvShow tvShow) {
        view.showDetails(tvShow);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.v("TAG_TAG", throwable.getLocalizedMessage());
    }
}

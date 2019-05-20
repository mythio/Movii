package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.tvShow.TvShow;

public class TvShowDetailsPresenter implements TvShowDetailsContract.Presenter, TvShowDetailsContract.Model.OnSeasonListener {

    private TvShowDetailsContract.View view;
    private TvShowDetailsModel model;

    public TvShowDetailsPresenter(TvShowDetailsContract.View view) {
        this.view = view;
        model = new TvShowDetailsModel();
    }

    @Override
    public void onRequestSeasons(String id) {
        model.getDetails(this, Integer.valueOf(id));
    }

    @Override
    public void onFinished(TvShow tvShow) {
        view.showDetails(tvShow);
    }

    @Override
    public void onFailure(String message) {
        Log.v("TAG_TAG", message);
    }
}

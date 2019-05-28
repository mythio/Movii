package com.mythio.movii.activity.TvShowDetails.contract;

public class TvShowDetailsPresenter implements TvShowDetailsContract.Presenter {

    private TvShowDetailsContract.View view;
    private TvShowDetailsModel model;

    public TvShowDetailsPresenter(TvShowDetailsContract.View view) {
        this.view = view;
        model = new TvShowDetailsModel();
    }

    @Override
    public void onRequestSeasons(String id) {
        model.getDetails(tvShow -> view.showDetails(tvShow), Integer.valueOf(id));
    }
}

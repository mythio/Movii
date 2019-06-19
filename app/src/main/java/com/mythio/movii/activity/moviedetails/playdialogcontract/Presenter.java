package com.mythio.movii.activity.moviedetails.playdialogcontract;

public class Presenter implements Contract.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void onViewInitialized() {
        view.initView();
    }

    @Override
    public void onStartTrailer() {
        view.launchYoutubePlayer();
    }

    @Override
    public void onStartStreaming() {
        view.launchBrowser();
    }

    @Override
    public void detachView() {
        view = null;
    }
}

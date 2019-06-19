package com.mythio.movii.activity.moviedetails.playdialogcontract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void initView();

        void launchYoutubePlayer();

        void launchBrowser();
    }

    interface Presenter extends BasePresenter {

        void onViewInitialized();

        void onStartTrailer();

        void onStartStreaming();
    }
}

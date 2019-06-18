package com.mythio.movii.activity.tvshowdetails.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.tvshow.TvShow;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showDetails(TvShow tvShow);
    }

    interface Presenter extends BasePresenter {

        void onRequestSeasons(String id);
    }
}

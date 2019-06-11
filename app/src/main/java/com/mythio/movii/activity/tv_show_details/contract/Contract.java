package com.mythio.movii.activity.tv_show_details.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.tv_show.TvShow;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showDetails(TvShow tvShow);
    }

    interface Presenter extends BasePresenter {

        void onRequestSeasons(String id);
    }
}

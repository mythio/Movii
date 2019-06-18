package com.mythio.movii.activity.searchtvshow.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.tvshow.TvShow;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showPlate();

        void hidePlate();

        void showSearchResult(ArrayList<TvShow> tvShows);
    }

    interface Presenter extends BasePresenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }
}

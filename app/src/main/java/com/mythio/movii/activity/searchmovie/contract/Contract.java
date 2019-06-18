package com.mythio.movii.activity.searchmovie.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showPlate();

        void hidePlate();

        void showSearchResult(ArrayList<Movie> movies);
    }

    interface Presenter extends BasePresenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }
}

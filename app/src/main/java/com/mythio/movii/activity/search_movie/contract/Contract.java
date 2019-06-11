package com.mythio.movii.activity.search_movie.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showPlate();

        void hidePlate();

        void showSearchResult(ArrayList<MovieTmdb> movies);
    }

    interface Presenter extends BasePresenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }

    interface Model {

        void getSearchResults(OnSearchResultsListener listener, String query);

        interface OnSearchResultsListener {

            void onFinished(ArrayList<MovieTmdb> movies);
        }
    }
}

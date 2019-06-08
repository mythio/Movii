package com.mythio.movii.activity.search_tv_show.contract;

import com.mythio.movii.model.tv_show.TvShowTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View {

        void showPlate();

        void hidePlate();

        void showSearchResult(ArrayList<TvShowTmdb> tvShows);
    }

    interface Presenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }

    interface Model {

        void getSearchResults(OnSearchResultsListener listener, String query);

        interface OnSearchResultsListener {

            void onFinished(ArrayList<TvShowTmdb> tvShows);
        }
    }
}

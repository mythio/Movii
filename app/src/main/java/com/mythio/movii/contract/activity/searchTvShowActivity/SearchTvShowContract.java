package com.mythio.movii.contract.activity.searchTvShowActivity;

import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public interface SearchTvShowContract {

    interface View {

        void showPlate();

        void hidePlate();

        void showRes(ArrayList<TvShowTmdb> tvShows);
    }

    interface Presenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }

    interface Model {

        void getSearchResults(OnTvShowSearchListener listener, String query);

        interface OnTvShowSearchListener {

            void onFinished(ArrayList<TvShowTmdb> tvShows);

            void onFailure(String message);
        }
    }
}

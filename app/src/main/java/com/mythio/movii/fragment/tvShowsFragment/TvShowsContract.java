package com.mythio.movii.fragment.tvShowsFragment;

import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public interface TvShowsContract {

    interface View {

        void initViewPager();

        void showSlideShow(ArrayList<TvShowTmdb> tvShowTmdbs);
    }

    interface Presenter {

        void initViews();

        void setDataToViewPager(ArrayList<TvShowTmdb> tvShows);
    }

    interface Callback {

        void onDataReceived(ArrayList<TvShowTmdb> tvShows);
    }
}

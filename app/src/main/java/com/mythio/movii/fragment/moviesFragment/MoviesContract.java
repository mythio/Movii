package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface MoviesContract {

    interface View {

        void initViewPager();

        void showSlideShow(ArrayList<MovieTmdb> movies);
    }

    interface Presenter {

        void initViews();

        void setDataToViewPager(ArrayList<MovieTmdb> movies);
    }

    interface Callback {

        void onDataReceived(ArrayList<MovieTmdb> movies);
    }
}

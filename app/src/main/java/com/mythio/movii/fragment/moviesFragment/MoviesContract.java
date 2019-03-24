package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public interface MoviesContract {

    interface View {

        void initViewPager();

        void showSlideShow(ArrayList<Movie> movies);
    }

    interface Presenter {

        void initViews();

        void setDataToViewPager(ArrayList<Movie> movies);
    }

    interface Callback {

        void onDataReceived(ArrayList<Movie> movies);
    }
}

package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface MoviesContract {

    interface View {

        void initViewPager();

        void showSlideShow(List<Movie> movies);
    }

    interface Presenter {

        void initViews();

        void setDataToViewPager(List<Movie> movies);
    }

    interface Callback {

        void onDataReceived(List<Movie> movies);
    }
}

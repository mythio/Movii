package com.mythio.movii.contract.MovieFragment;

import android.support.v4.view.ViewPager;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface MovieFragmentContract {

    interface View {

        void setSlideShow(ViewPager viewPager);
        void showSlideShow(List<Movie> movies);
    }

    interface Presenter {

        void onDataRequest();

        void onInitViewPager(ViewPager viewPager);
    }

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Movie> movies);
            void onFailure(Throwable throwable);
        }

        void getData(OnFinishedListener onFinishedListener);
    }
}
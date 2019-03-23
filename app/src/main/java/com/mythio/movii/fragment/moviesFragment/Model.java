package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

interface Model {

    void getData(OnFinishedListener onFinishedListener);

    interface OnFinishedListener {

        void onFinished(List<Movie> movies);

        void onFailure(Throwable throwable);
    }
}
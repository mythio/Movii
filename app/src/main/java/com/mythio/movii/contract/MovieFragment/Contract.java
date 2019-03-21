package com.mythio.movii.contract.MovieFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface Contract {

    interface View {

        void showText(List<Movie> movies);
    }

    interface Presenter {

        void requestData();
    }

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Movie> movies);
            void onFailure(Throwable throwable);
        }

        void getData(OnFinishedListener onFinishedListener);
    }
}
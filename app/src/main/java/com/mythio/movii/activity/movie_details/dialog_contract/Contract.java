package com.mythio.movii.activity.movie_details.dialog_contract;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View {

        void showInitView();

        void showRecommendedMovies(ArrayList<MovieTmdb> movies);
    }

    interface Presenter {

        void onViewInitialized();

        void getData(int id);
    }

    interface Model {

        void getMovies(int id, OnMoviesReceivedListener listener);

        interface OnMoviesReceivedListener {

            void onFinished(ArrayList<MovieTmdb> movies);
        }
    }

}

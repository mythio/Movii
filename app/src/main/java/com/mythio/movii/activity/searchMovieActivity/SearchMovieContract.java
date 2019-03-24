package com.mythio.movii.activity.searchMovieActivity;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface SearchMovieContract {

    interface View {

        void showPlate();

        void showRes(ArrayList<MovieTmdb> movieTmdbArrayList);
    }

    interface Presenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }

    interface Model {

        void getSearchResults(OnMoviesSearchListener listener, String query);

        interface OnMoviesSearchListener {

            void onFinished(ArrayList<MovieTmdb> movieTmdbArrayList);

            void onFailure(Throwable throwable);
        }
    }
}

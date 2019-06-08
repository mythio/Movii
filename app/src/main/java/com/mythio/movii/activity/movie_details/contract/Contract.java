package com.mythio.movii.activity.movie_details.contract;

import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View {

        void showMovieDetails(Movie movie);

        void showCastRecyclerView(ArrayList<Cast> casts);

        void showRecommendationsRecyclerView(ArrayList<MovieTmdb> movies);
    }

    interface Presenter {

        void getDetails(int id);
    }

    interface Model {

        void getDetails(OnMovieDetailReceivedListener listener, int id);

        interface OnMovieDetailReceivedListener {

            void onFinished(Movie movie);
        }
    }
}

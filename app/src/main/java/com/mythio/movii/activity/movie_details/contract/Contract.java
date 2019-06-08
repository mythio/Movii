package com.mythio.movii.activity.movie_details.contract;

import com.mythio.movii.model.movie.Movie;

public interface Contract {

    interface View {

        void showMovieDetails(Movie movie);
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

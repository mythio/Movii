package com.mythio.movii.activity.movie_details.contract;

import com.mythio.movii.model.movie.Movie;

public interface Contract {

    interface View {

        void showDetails(Movie movie);
    }

    interface Presenter {

        void getDetails(String id);
    }

    interface Model {

        void getDetails(MovieDetailsListener listener, int id);

        interface MovieDetailsListener {

            void onFinished(Movie movie);
        }
    }
}

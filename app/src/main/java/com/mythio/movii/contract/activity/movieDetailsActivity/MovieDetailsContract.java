package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.movie.Movie;

public interface MovieDetailsContract {

    interface View {

        void showDetails(Movie movie);
    }

    interface Presenter {

        void getDetails(String id);
    }

    interface Model {

        void getDetails(MovieDetailsListener listener, Integer id);

        interface MovieDetailsListener {

            void onFinished(Movie movie);

            void onFailure(String message);
        }
    }
}

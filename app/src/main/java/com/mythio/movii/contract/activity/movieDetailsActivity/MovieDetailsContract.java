package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.movie.Movie;

public interface MovieDetailsContract {

    interface View {

        void showDetails(Movie movie);

        void showCast();

        void showSimilarMovies();
    }

    interface Presenter {

        void onDetailsRecieved();
    }

    interface Model {

        void getMovieDetails(OnMovieDetailsListener listener, Integer id);

        interface OnMovieDetailsListener {

            void onFinished(Movie movie);

            void onFailure(Throwable throwable);
        }
    }
}

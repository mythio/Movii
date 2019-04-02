package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public interface MovieDetailsContract {

    interface View {

        void showPages(ArrayList<Movie> movies);
    }

    interface Presenter {

        void onRequestCollection(String id);
    }

    interface Model {

        void getDetails(OnCollectionListener listener, Integer id);

        interface OnCollectionListener {

            void onFinished(ArrayList<Movie> movies);

            void onFailure(Throwable throwable);
        }
    }
}

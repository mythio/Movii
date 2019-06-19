package com.mythio.movii.activity.moviedetails.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showPlayButton();

        void showMovieDetails(Movie movie);

        void showCastRecyclerView(ArrayList<Cast> casts);

        void showRecommendationsRecyclerView(ArrayList<Movie> movies);

        void streamInBrowser(String imdbId, String ticket);
    }

    interface Presenter extends BasePresenter {

        void getDetails(int id);

        void onGetStreamLink(String imdbId);
    }
}

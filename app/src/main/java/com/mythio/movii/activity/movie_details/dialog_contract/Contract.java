package com.mythio.movii.activity.movie_details.dialog_contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showInitView();

        void showRecommendedMovies(ArrayList<Movie> movies);
    }

    interface Presenter extends BasePresenter {

        void onViewInitialized();

        void getData(int id);
    }
}

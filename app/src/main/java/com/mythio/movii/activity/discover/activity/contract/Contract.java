package com.mythio.movii.activity.discover.activity.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.discover.fragment.contract.FragmentNavigation;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.tvshow.TvShow;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showFragment(BaseDiscoverFragment fragment);

        void sendToMoviesFragment(ArrayList<Movie> movies);

        void sendToTvShowsFragment(ArrayList<TvShow> tvShows);
    }

    interface Presenter extends FragmentNavigation, BasePresenter {

        void getData();
    }
}

package com.mythio.movii.activity.discover.activity.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;
import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.discover.fragment.contract.FragmentNavigation;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tv_show.TvShowTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View extends BaseView<Presenter> {

        void showFragment(BaseDiscoverFragment fragment);

        void sendToMoviesFragment(ArrayList<MovieTmdb> movies);

        void sendToTvShowsFragment(ArrayList<TvShowTmdb> tvShows);
    }

    interface Presenter extends BasePresenter, FragmentNavigation {

        void getData();
    }
}

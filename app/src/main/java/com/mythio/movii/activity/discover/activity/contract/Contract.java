package com.mythio.movii.activity.discover.activity.contract;

import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public interface Contract {

    interface View {

        void showFragment(BaseDiscoverFragment fragment);

        void sendToMoviesFragment(ArrayList<MovieTmdb> movies);

        void sendToTvShowsFragment(ArrayList<TvShowTmdb> tvShows);
    }

    interface Presenter {

        void getData();
    }

    interface Model {

        interface MoviesModel {

            void getMovies(MoviesListener listener);

            interface MoviesListener {

                void onFinishedMovies(ArrayList<MovieTmdb> movies);
            }
        }

        interface TvShowsModel {

            void getTvShows(TvShowsListener listener);

            interface TvShowsListener {

                void onFinishedTvShows(ArrayList<TvShowTmdb> tvShows);
            }
        }
    }
}

package com.mythio.movii.contract.activity.discoverActivity;

import com.mythio.movii.fragment.discover.BaseDiscoverFragment;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public interface DiscoverContract {

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

package com.mythio.movii.contract.activity.discoverActivity;

import com.mythio.movii.fragment.BaseFragment;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public interface DiscoverContract {

    interface View {

        void showFragment(BaseFragment fragment);

        void sendToFragmentMovies(ArrayList<MovieTmdb> movies);

        void sendToFragmentTvShows(ArrayList<TvShowTmdb> tvShows);
    }

    interface Presenter {

        void onDataRequest();
    }

    interface Model {

        interface MoviesModel {

            void getMovies(MoviesListener moviesListener);

            interface MoviesListener {

                void onFinishedMovies(ArrayList<MovieTmdb> movies);

                void onFailureMovies(Throwable throwable);
            }
        }

        interface TvShowsModel {

            void getTvShows(TvShowsListener tvShowsListener);

            interface TvShowsListener {

                void onFinishedTvShows(ArrayList<TvShowTmdb> tvShows);

                void onFailureTvShows(Throwable throwable);

            }
        }
    }
}

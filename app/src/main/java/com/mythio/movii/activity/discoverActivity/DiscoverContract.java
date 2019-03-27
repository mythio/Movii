package com.mythio.movii.activity.discoverActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.tvShow.TvShow;

import java.util.ArrayList;

public interface DiscoverContract {

    interface View {

        void showFragment(BaseFragment fragment);

        void sendToFragmentMovies(ArrayList<Movie> movies);

        void sendToFragmentTvShows(ArrayList<TvShow> tvShows);
    }

    interface Presenter {

        void onMovieDataRequest();

        void onTvShowDataRequest();
    }

    interface Model {

        interface MoviesModel {

            void getMovies(MoviesListener moviesListener);

            interface MoviesListener {

                void onFinishedMovies(ArrayList<Movie> movies);

                void onFailureMovies(Throwable throwable);
            }
        }

        interface TvShowsModel {

            void getTvShows(TvShowsListener tvShowsListener);

            interface TvShowsListener {

                void onFinishedTvShows(ArrayList<TvShow> tvShows);

                void onFailureTvShows(Throwable throwable);

            }
        }
    }
}

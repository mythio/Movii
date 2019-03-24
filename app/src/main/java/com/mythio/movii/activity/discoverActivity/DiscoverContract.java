package com.mythio.movii.activity.discoverActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface DiscoverContract {

    interface View {

        void showFragment(BaseFragment fragment);

        void sendToFragment(List<Movie> movies);
    }

    interface Presenter {

        void onDataRequest();
    }

    interface Model {

        interface MoviesModel {

            void getMovies(MoviesListener moviesListener);

            interface MoviesListener {

                void onFinished(List<Movie> movies);

                void onFailure(Throwable throwable);
            }
        }

        interface TvShowsModel {
            // TODO: 3/24/19 Create tv shows model
        }
    }
}

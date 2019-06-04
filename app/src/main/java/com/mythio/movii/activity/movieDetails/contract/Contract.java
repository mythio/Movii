package com.mythio.movii.activity.movieDetails.contract;

import com.mythio.movii.model.movie.Movie;

public interface Contract {

    interface View {

        void showDetails(Movie movie);
    }

    interface Presenter {

        void getDetails(String id);
    }

    interface Model {

        void getDetails(MovieDetailsListener listener, int id);

        void getStreamingLinks(StreamingListener listener, int id);

        interface MovieDetailsListener {

            void onFinished(Movie movie);
        }

        interface StreamingListener {

            void onFinished(String url);
        }
    }
}

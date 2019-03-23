package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface Contract {

    interface View {

        void showFragment(BaseFragment fragment);

        void sendToFragment(List<Movie> movies);
    }

    interface Presenter {

        void onDataRequest();
    }

    interface Model {

        void getData(OnDataReceived onDataReceived);

        interface OnDataReceived {

            void onFinished(List<Movie> movies);

            void onFailure(Throwable throwable);
        }
    }
}

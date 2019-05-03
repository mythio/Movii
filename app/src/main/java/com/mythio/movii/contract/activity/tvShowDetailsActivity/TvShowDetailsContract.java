package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import com.mythio.movii.model.tvShow.TvShow;

public interface TvShowDetailsContract {

    interface View {

        void showDetails(TvShow tvShow);
    }

    interface Presenter {

        void onGetDetails(Integer id);
    }

    interface Model {

        void getTvShowDetails(OnTvShowDetailsListener listener, Integer id);

        interface OnTvShowDetailsListener {

            void onFinished(TvShow tvShow);

            void onFailure(String message);
        }
    }
}

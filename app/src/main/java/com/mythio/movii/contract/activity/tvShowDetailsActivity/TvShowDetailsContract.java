package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import com.mythio.movii.model.tvShow.TvShow;

public interface TvShowDetailsContract {

    interface View {

        void showDetails(TvShow tvShow);
    }

    interface Presenter {

        void onRequestSeasons(String id);
    }

    interface Model {

        void getDetails(OnSeasonListener listener, Integer id);

        interface OnSeasonListener {

            void onFinished(TvShow tvShow);

            void onFailure(String message);
        }
    }
}

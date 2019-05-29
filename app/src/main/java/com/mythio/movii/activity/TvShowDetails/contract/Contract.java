package com.mythio.movii.activity.TvShowDetails.contract;

import com.mythio.movii.model.tvShow.TvShow;

public interface Contract {

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
        }
    }
}

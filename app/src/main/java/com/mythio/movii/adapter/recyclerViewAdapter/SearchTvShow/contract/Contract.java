package com.mythio.movii.adapter.recyclerViewAdapter.SearchTvShow.contract;

import com.mythio.movii.model.tvShow.TvShowTmdb;

public interface Contract {

    interface View {

        void show(TvShowTmdb tvShowTmdb);
    }

    interface Presenter {

        void onBindViewAtPosition(View view, int position);

        int getCount();
    }
}

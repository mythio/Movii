package com.mythio.movii.adapter.recyclerViewAdapter.SearchMovie.contract;

import com.mythio.movii.model.movie.MovieTmdb;

public interface Contract {

    interface View {

        void show(MovieTmdb movieTmdb);
    }

    interface Presenter {

        void onBindViewAtPosition(View view, int position);

        int getCount();
    }
}

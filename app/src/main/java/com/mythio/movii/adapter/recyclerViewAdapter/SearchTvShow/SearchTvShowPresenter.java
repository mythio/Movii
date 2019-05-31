package com.mythio.movii.adapter.recyclerViewAdapter.SearchTvShow;

import com.mythio.movii.adapter.recyclerViewAdapter.Contract;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class SearchTvShowPresenter implements Contract.Presenter<TvShowTmdb> {
    private final ArrayList<TvShowTmdb> tvShows;

    public SearchTvShowPresenter(ArrayList<TvShowTmdb> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public void onBindViewAtPosition(Contract.View<TvShowTmdb> view, int position) {
        view.show(tvShows.get(position));
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }
}

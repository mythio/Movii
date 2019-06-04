package com.mythio.movii.adapter.recycler_view_adapter.SearchTvShow;

import androidx.annotation.NonNull;

import com.mythio.movii.adapter.recycler_view_adapter.Contract;
import com.mythio.movii.model.tv_show.TvShowTmdb;

import java.util.ArrayList;

public class SearchTvShowPresenter implements Contract.Presenter<TvShowTmdb> {
    private final ArrayList<TvShowTmdb> tvShows;

    public SearchTvShowPresenter(ArrayList<TvShowTmdb> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public void onBindViewAtPosition(@NonNull Contract.View<TvShowTmdb> view, int position) {
        view.show(tvShows.get(position));
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }
}

package com.mythio.movii.adapter.search_tv_show;

import androidx.annotation.NonNull;

import com.mythio.movii.adapter.Contract;
import com.mythio.movii.model.tvshow.TvShow;

import java.util.ArrayList;

public class SearchTvShowPresenter implements Contract.Presenter<TvShow> {
    private final ArrayList<TvShow> tvShows;

    public SearchTvShowPresenter(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public void onBindViewAtPosition(@NonNull Contract.View<TvShow> view, int position) {
        view.show(tvShows.get(position));
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }
}

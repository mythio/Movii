package com.mythio.movii.adapter.popular_tvshow;

import com.mythio.movii.adapter.Contract;
import com.mythio.movii.model.tvshow.TvShow;

import java.util.ArrayList;

public class PopularTvshowPresenter implements Contract.Presenter<TvShow> {
    private final ArrayList<TvShow> tvShows;

    public PopularTvshowPresenter(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public void onBindViewAtPosition(Contract.View<TvShow> view, int position) {
        view.show(tvShows.get(position));
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }
}

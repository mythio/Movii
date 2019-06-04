package com.mythio.movii.activity.discover.fragment.contract;

import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class TvShowsPresenter implements TvShowsContract.Presenter {

    private final TvShowsContract.View view;

    public TvShowsPresenter(TvShowsContract.View view) {
        this.view = view;
    }

    @Override
    public void initViews() {
        view.initViewPager();
    }

    @Override
    public void setDataToViewPager(ArrayList<TvShowTmdb> tvShows) {
        view.showSlideShow(tvShows);
    }
}

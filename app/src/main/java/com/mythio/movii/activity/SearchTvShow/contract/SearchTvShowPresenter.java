package com.mythio.movii.activity.SearchTvShow.contract;

import android.util.Log;

import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class SearchTvShowPresenter implements SearchTvShowContract.Presenter, SearchTvShowContract.Model.OnTvShowSearchListener {

    private SearchTvShowContract.View view;
    private SearchTvShowContract.Model model;

    public SearchTvShowPresenter(SearchTvShowContract.View view) {
        this.view = view;
        model = new SearchTvShowModel();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(this, string);
        view.hidePlate();
    }

    @Override
    public void onFinished(ArrayList<TvShowTmdb> tvShows) {
        view.showRes(tvShows);
    }

    @Override
    public void onFailure(String message) {
        Log.v("TAG_TAG", message);
    }
}

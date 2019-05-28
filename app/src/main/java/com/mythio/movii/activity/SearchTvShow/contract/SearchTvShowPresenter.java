package com.mythio.movii.activity.SearchTvShow.contract;

public class SearchTvShowPresenter implements SearchTvShowContract.Presenter {

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
        model.getSearchResults(tvShows -> view.showRes(tvShows), string);
        view.hidePlate();
    }
}
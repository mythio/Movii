package com.mythio.movii.activity.search_tv_show.contract;

import androidx.annotation.NonNull;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    @NonNull
    private final Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(view::showRes, string);
        view.hidePlate();
    }
}
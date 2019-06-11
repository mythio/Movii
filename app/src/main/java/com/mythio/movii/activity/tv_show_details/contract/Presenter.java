package com.mythio.movii.activity.tv_show_details.contract;

import androidx.annotation.NonNull;

public class Presenter implements Contract.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void onRequestSeasons(@NonNull String id) {
//        model.getDetails(view::showDetails, Integer.valueOf(id));
    }

    @Override
    public void detachView() {
        view = null;
    }
}

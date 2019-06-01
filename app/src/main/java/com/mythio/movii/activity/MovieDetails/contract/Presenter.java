package com.mythio.movii.activity.MovieDetails.contract;

import androidx.annotation.NonNull;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    @NonNull
    private final Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void getDetails(@NonNull String id) {
        model.getDetails(view::showDetails, Integer.valueOf(id));
    }
}
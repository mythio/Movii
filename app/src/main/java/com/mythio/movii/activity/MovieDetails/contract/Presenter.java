package com.mythio.movii.activity.MovieDetails.contract;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    private final Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void getDetails(String id) {
        model.getDetails(view::showDetails, Integer.valueOf(id));
    }
}
package com.mythio.movii.activity.TvShowDetails.contract;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    private final Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onRequestSeasons(String id) {
        model.getDetails(view::showDetails, Integer.valueOf(id));
    }
}

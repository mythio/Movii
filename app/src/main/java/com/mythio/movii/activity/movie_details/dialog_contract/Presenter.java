package com.mythio.movii.activity.movie_details.dialog_contract;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    private final Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onLaunch(int id) {
        model.getMovies(id, view::initSheet);
    }
}

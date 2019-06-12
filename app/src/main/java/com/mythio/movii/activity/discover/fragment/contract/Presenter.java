package com.mythio.movii.activity.discover.fragment.contract;

import java.util.ArrayList;

public class Presenter<T> implements Contract.Presenter<T> {

    private Contract.View<T> view;

    public Presenter(Contract.View<T> view) {
        this.view = view;
    }

    @Override
    public void setDataToViewPager(ArrayList<T> t) {
        view.showSlideShow(t);
    }

    @Override
    public void detachView() {
        view = null;
    }
}

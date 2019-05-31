package com.mythio.movii.adapter.recyclerViewAdapter.Cast;

import com.mythio.movii.adapter.recyclerViewAdapter.Contract;
import com.mythio.movii.model.cast.Cast;

import java.util.ArrayList;

public class CastPresenter implements Contract.Presenter<Cast> {
    private final ArrayList<Cast> casts;

    public CastPresenter(ArrayList<Cast> casts) {
        this.casts = casts;
    }

    @Override
    public void onBindViewAtPosition(Contract.View<Cast> view, int position) {
        view.show(casts.get(position));
    }

    @Override
    public int getCount() {
        return casts.size();
    }
}

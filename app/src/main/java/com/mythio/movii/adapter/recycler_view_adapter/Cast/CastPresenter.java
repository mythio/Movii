package com.mythio.movii.adapter.recycler_view_adapter.Cast;

import androidx.annotation.NonNull;

import com.mythio.movii.adapter.recycler_view_adapter.Contract;
import com.mythio.movii.model.cast.Cast;

import java.util.ArrayList;

public class CastPresenter implements Contract.Presenter<Cast> {
    private final ArrayList<Cast> casts;

    public CastPresenter(ArrayList<Cast> casts) {
        this.casts = casts;
    }

    @Override
    public void onBindViewAtPosition(@NonNull Contract.View<Cast> view, int position) {
        view.show(casts.get(position));
    }

    @Override
    public int getCount() {
        return casts.size();
    }
}

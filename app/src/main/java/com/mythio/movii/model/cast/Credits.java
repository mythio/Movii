package com.mythio.movii.model.cast;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Credits {

    @Nullable
    @SerializedName("cast")
    private ArrayList<Cast> cast = null;

    @Nullable
    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}

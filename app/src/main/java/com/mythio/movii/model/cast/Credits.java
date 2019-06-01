package com.mythio.movii.model.cast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Credits {

    @SerializedName("cast")
    private ArrayList<Cast> cast = null;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}

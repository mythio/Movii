package com.mythio.movii.model.cast;

import com.google.gson.annotations.SerializedName;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class CastMovies {

    @SerializedName("cast")
    private ArrayList<MovieTmdb> cast;

    public ArrayList<MovieTmdb> getCast() {
        return cast;
    }

    public void setCast(ArrayList<MovieTmdb> cast) {
        this.cast = cast;
    }
}

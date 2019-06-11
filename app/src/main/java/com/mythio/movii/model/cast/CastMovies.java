package com.mythio.movii.model.cast;

import com.google.gson.annotations.SerializedName;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class CastMovies {

    @SerializedName("cast")
    private ArrayList<Movie> cast;

    public ArrayList<Movie> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Movie> cast) {
        this.cast = cast;
    }
}

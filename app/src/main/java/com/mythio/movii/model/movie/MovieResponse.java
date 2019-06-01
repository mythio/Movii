package com.mythio.movii.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<MovieTmdb> results = null;

    public ArrayList<MovieTmdb> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieTmdb> results) {
        this.results = results;
    }
}

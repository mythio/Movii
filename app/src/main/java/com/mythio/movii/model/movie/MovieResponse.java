package com.mythio.movii.model.movie;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @Nullable
    @SerializedName("results")
    private ArrayList<MovieTmdb> results = null;

    @Nullable
    public ArrayList<MovieTmdb> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieTmdb> results) {
        this.results = results;
    }
}

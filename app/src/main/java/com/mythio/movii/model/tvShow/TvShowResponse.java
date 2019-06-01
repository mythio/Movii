package com.mythio.movii.model.tvShow;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowResponse {

    @SerializedName("results")
    private ArrayList<TvShowTmdb> results = null;

    public ArrayList<TvShowTmdb> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvShowTmdb> results) {
        this.results = results;
    }
}

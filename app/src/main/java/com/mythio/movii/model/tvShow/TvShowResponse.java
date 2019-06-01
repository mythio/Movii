package com.mythio.movii.model.tvShow;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowResponse {

    @Nullable
    @SerializedName("results")
    private ArrayList<TvShowTmdb> results = null;

    @Nullable
    public ArrayList<TvShowTmdb> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvShowTmdb> results) {
        this.results = results;
    }
}

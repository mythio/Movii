package com.mythio.movii.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<MovieTmdb> results = null;

    public List<MovieTmdb> getResults() {
        return results;
    }

    public void setResults(List<MovieTmdb> results) {
        this.results = results;
    }
}

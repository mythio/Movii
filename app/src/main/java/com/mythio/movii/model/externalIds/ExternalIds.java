package com.mythio.movii.model.externalIds;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {

    @SerializedName("imdb_id")
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}

package com.mythio.movii.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName(value = "overview", alternate = {"plot"})
    public String overview;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("id")
    public Integer id;

    @SerializedName("title")
    public String title;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName(value = "vote_count", alternate = {""})
    public Integer voteCount;

    @SerializedName("vote_average")
    public Double voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}

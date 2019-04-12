package com.mythio.movii.model.collection;

import com.google.gson.annotations.SerializedName;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class CollectionResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("parts")
    private ArrayList<MovieTmdb> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public ArrayList<MovieTmdb> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieTmdb> movies) {
        this.movies = movies;
    }
}

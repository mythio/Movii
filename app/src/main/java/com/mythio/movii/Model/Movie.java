package com.mythio.movii.Model;

import java.util.List;

public class Movie {
    private String imdb;
    private String poster_path;
    private String title;
    private String imdb_id;
    private String tmdb_id;
    private String release_date;
    private String overview;
    private String length;
    private List<String> starCast;
    private Double ratings;
    private Integer ratingsCount;
    private String genre;

    public Movie(String poster_path, String title) {
        this.poster_path = poster_path;
        this.title = title;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public List<String> getStarCast() {
        return starCast;
    }

    public void setStarCast(List<String> starCast) {
        this.starCast = starCast;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }
}
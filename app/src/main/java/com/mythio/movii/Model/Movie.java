package com.mythio.movii.Model;

public class Movie {
    private String imdb;
    private String poster_path;
    private String original_title;
    private String imdb_id;
    private String release_date;
    private String overview;
    private String production;
    private String backdrop_path;

    public Movie(String original_title, String poster_path, String backdrop_path) {
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public String getImdb() {
        return imdb;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getProduction() {
        return production;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
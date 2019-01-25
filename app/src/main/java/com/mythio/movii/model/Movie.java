package com.mythio.movii.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String imdb;
    private String poster_path;
    private String title1;
    private String title2;
    private String imdb_id;
    private String tmdb_id;
    private String release_date;
    private String overview;
    private String length;
    private String cast;
    private Double ratings;
    private String voteCount;
    private String genre;
    private String video_key;

    public Movie(String poster_path, String title1, String title2) {
        this.poster_path = poster_path;
        this.title1 = title1;
        this.title2 = title2;
    }

    public Movie(String poster_path, String title1, String title2, String tmdb_id, String genre, String overview, String release_date) {
        this.poster_path = poster_path;
        this.title1 = title1;
        this.title2 = title2;
        this.tmdb_id = tmdb_id;
        this.genre = genre;
        this.overview = overview;
        this.release_date = release_date;
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

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(String tmdb_id) {
        this.tmdb_id = tmdb_id;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String ratingsCount) {
        this.voteCount = ratingsCount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVideo_key() {
        return video_key;
    }

    public void setVideo_key(String video_key) {
        this.video_key = video_key;
    }
}
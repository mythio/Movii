package com.mythio.movii.model.movie;

import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.video.Video;

import java.util.ArrayList;

public class Movie {

    private String posterPath;
    private String overview;
    private String id;
    private String collectionId;
    private String collectionName;
    private String collectionPosterPath;
    private String collectionBackdrop;
    private String title;
    private String backdropPath;
    private String votes;
    private String rating;
    private String imdb;
    private String genres;
    private String year;
    private String runtime;
    private ArrayList<Video> videos;
    private ArrayList<MovieTmdb> moviesTmdb;
    private ArrayList<Cast> casts;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionPosterPath() {
        return collectionPosterPath;
    }

    public void setCollectionPosterPath(String collectionPosterPath) {
        this.collectionPosterPath = collectionPosterPath;
    }

    public String getCollectionBackdrop() {
        return collectionBackdrop;
    }

    public void setCollectionBackdrop(String collectionBackdrop) {
        this.collectionBackdrop = collectionBackdrop;
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

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public ArrayList<MovieTmdb> getMoviesTmdb() {
        return moviesTmdb;
    }

    public void setMoviesTmdb(ArrayList<MovieTmdb> moviesTmdb) {
        this.moviesTmdb = moviesTmdb;
    }

    public ArrayList<Cast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Cast> casts) {
        this.casts = casts;
    }
}

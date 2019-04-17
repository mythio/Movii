package com.mythio.movii.model.tvShow;

import com.mythio.movii.model.season.SeasonDetails;
import com.mythio.movii.model.video.Video;

import java.util.ArrayList;

public class TvShow {

    private String backdropPath;
    private String genres;
    private String id;
    private String name;
    private String overview;
    private String posterPath;
    private ArrayList<SeasonDetails> seasons;
    private ArrayList<Video> videos;
    private String imdb;
    private String rating;
    private String votes;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ArrayList<SeasonDetails> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<SeasonDetails> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}

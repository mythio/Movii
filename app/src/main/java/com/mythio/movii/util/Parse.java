package com.mythio.movii.util;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;

import java.text.NumberFormat;
import java.util.Locale;

/*
 *  Helper class to parse data
 */

public class Parse {

    public static Movie getMovie(MovieTmdb movieTmdb, MovieOmdb movieOmdb) {

        Movie movie = new Movie();

        movie.setBackdropPath(movieTmdb.getBackdropPath());
        movie.setCasts(movieTmdb.getCredits().getCast());
        movie.setId(movieTmdb.getId().toString());
        movie.setPosterPath(movieTmdb.getPosterPath());
        movie.setTitle(movieTmdb.getTitle());
        movie.setVideos(movieTmdb.getVideoResponse().getVideos());
        movie.setImdb(movieTmdb.getImdb());
        movie.setRecommendations(movieTmdb.getRecommendations().getResults());
        movie.setRating(movieOmdb.getImdbRating() == null
                ? String.valueOf(movieTmdb.getVoteAverage())
                : movieOmdb.getImdbRating());
        movie.setVotes(movieOmdb.getImdbVotes() == null
                ? NumberFormat.getNumberInstance(Locale.US).format(movieTmdb.getVoteCount())
                : movieOmdb.getImdbVotes());
        return movie;
    }
}

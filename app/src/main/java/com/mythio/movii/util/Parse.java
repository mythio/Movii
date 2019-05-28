package com.mythio.movii.util;

import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

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
        movie.setOverview(movieTmdb.getOverview());

        String[] title_arr = movieTmdb.getTitle().split(": ");

        if (title_arr.length == 2) {
            movie.setTitle1(title_arr[0].trim());
            movie.setTitle2(title_arr[1].trim());
        } else {
            movie.setTitle1(title_arr[0].trim());
            movie.setTitle2("");
        }

        ArrayList<Genre> genres = movieTmdb.getGenres();
        StringBuilder genreString = new StringBuilder();

        for (int i = 0; i < genres.size(); ++i) {
            genreString.append(genres.get(i).getName());
            if (i < genres.size() - 1) {
                genreString.append("  |  ");
            }
        }

        movie.setGenres(genreString.toString());
        movie.setYear(new StringTokenizer(movieTmdb.getReleaseDate(), "-").nextToken());
        movie.setRuntime(movieTmdb.getRuntime() / 60 + ":" + movieTmdb.getRuntime() % 60);
        movie.setRating(movieOmdb.getImdbRating() == null || movieOmdb.getImdbRating().equals("N/A")
                ? String.valueOf(movieTmdb.getVoteAverage())
                : movieOmdb.getImdbRating());
        movie.setVotes(movieOmdb.getImdbVotes() == null || movieOmdb.getImdbVotes().equals("N/A")
                ? NumberFormat.getNumberInstance(Locale.US).format(movieTmdb.getVoteCount())
                : movieOmdb.getImdbVotes());
        movie.setVideos(movieTmdb.getVideoResponse().getVideos());
        movie.setImdb(movieTmdb.getImdb());
        movie.setRecommendations(movieTmdb.getRecommendations().getResults());

        return movie;
    }
}

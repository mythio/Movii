package com.mythio.movii.util;

import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShow;
import com.mythio.movii.model.tvShow.TvShowOmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class Utils {

    public static Movie getMovie(MovieTmdb movieTmdb, MovieOmdb movieOmdb) {

        Movie movie = new Movie();

        String[] title_arr = movieTmdb.getTitle().split(": ");

        if (title_arr.length == 2) {
            movie.setTitle1(title_arr[0].trim());
            movie.setTitle2(title_arr[1].trim());
        } else {
            movie.setTitle1(title_arr[0].trim());
            movie.setTitle2("");
        }

        StringBuilder genreString = new StringBuilder();

        int s = Math.min(3, movieTmdb.getGenres().size());

        for (int i = 0; i < s; ++i) {
            genreString.append(movieTmdb.getGenres().get(i).getName());
            if (i < s - 1) {
                genreString.append("  |  ");
            }
        }

        ArrayList<Cast> casts = new ArrayList<>();

        for (Cast cast : movieTmdb.getCredits().getCast()) {
            if (cast.getProfilePath() != null) {
                casts.add(cast);
            }
        }

        movie.setBackdropPath(movieTmdb.getBackdropPath());
        movie.setId(movieTmdb.getId().toString());
        movie.setPosterPath(movieTmdb.getPosterPath());
        movie.setOverview(movieTmdb.getOverview());
        movie.setGenres(genreString.toString());
        movie.setCasts(casts);
        movie.setYear(new StringTokenizer(movieTmdb.getReleaseDate(), "-").nextToken());
        movie.setRuntime(
                String.format(Locale.US, "%02d", movieTmdb.getRuntime() / 60) +
                        ":" + String.format(Locale.US, "%02d", movieTmdb.getRuntime() % 60));
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

    public static TvShow getTvShow(TvShowTmdb tvShowTmdb, TvShowOmdb tvShowOmdb) {

        TvShow tvShow = new TvShow();

        // TODO: 29/5/19 parse tmdb + omdb to give tvShow

        return tvShow;
    }
}

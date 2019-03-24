package com.mythio.movii.activity.discoverActivity;

import android.util.Log;

import com.mythio.movii.activity.discoverActivity.DiscoverContract.Model;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverMoviesModel implements Model.MoviesModel {

    /*
    MOVIE FRAGMENT DATA
     */

    private Model.MoviesModel.MoviesListener moviesListener;
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);
    private ArrayList<MovieTmdb> movieTmdbList = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    public void getMovies(final Model.MoviesModel.MoviesListener moviesListener) {
        this.moviesListener = moviesListener;

        Call<MovieResponse> call = apiServiceTmdb.getPopularMovies(API_KEY_TMDB);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<MovieTmdb> movieTmdbList = response.body().getResults();
                getTMDB(movieTmdbList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                moviesListener.onFailure(t);
                Log.v("TAG_TAG", t.getLocalizedMessage());
            }
        });
    }

    private void getTMDB(final ArrayList<MovieTmdb> movies) {

        Call<MovieTmdb> call;
        final int[] pos = {0};
        for (final MovieTmdb movieTmdb : movies) {

            call = apiServiceTmdb.getMovieDetail(movieTmdb.getId(), API_KEY_TMDB, "videos");
            call.enqueue(new Callback<MovieTmdb>() {
                @Override
                public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {

                    movieTmdbList.add(response.body());
                    ++pos[0];
                    if (pos[0] == 20) {
                        getOMDB();
                    }
                }

                @Override
                public void onFailure(Call<MovieTmdb> call, Throwable t) {
                    moviesListener.onFailure(t);
                    Log.v("TAG_TAG", t.getLocalizedMessage());
                }
            });
        }
    }

    private void getOMDB() {

        final int[] pos = {0};
        for (final MovieTmdb movieTmdb : movieTmdbList) {

            Call<MovieOmdb> call = apiServiceOmdb.getOmdbDetail(API_KEY_OMDB, movieTmdb.getImdb());
            call.enqueue(new Callback<MovieOmdb>() {
                @Override
                public void onResponse(Call<MovieOmdb> call, Response<MovieOmdb> response) {

                    MovieOmdb movieOmdb = response.body();

                    Movie movie = new Movie();

                    movie.setPosterPath(movieTmdb.getPosterPath());
                    movie.setId(movieTmdb.getId());
                    movie.setTitle(movieTmdb.getTitle());
                    movie.setBackdropPath(movieTmdb.getBackdropPath());
                    movie.setImdb(movieTmdb.getImdb());
                    movie.setOverview(movieOmdb.getPlot().equals("N/A") ? movieTmdb.getOverview() : movieOmdb.getPlot());
                    movie.setVotes(movieOmdb.getImdbVotes().equals("N/A") ? String.valueOf(movieTmdb.getVoteCount()) : movieOmdb.getImdbVotes());
                    movie.setRating(movieOmdb.getImdbRating().equals("N/A") ? String.valueOf(movieTmdb.getVoteAverage()) : movieOmdb.getImdbRating());
                    movie.setYear(movieOmdb.getYear());
                    movie.setRuntime(movieOmdb.getRuntime());
                    movie.setVideos(movieTmdb.getVideoResponse().getVideos());

                    StringBuilder genreString = new StringBuilder();
                    if (movieOmdb.getGenre().equals("N/A")) {

                        int s = movieTmdb.getGenres().size();
                        for (Genre genre : movieTmdb.getGenres()) {
                            genreString.append(genre.getName());
                            s--;
                            if (s > 0) {
                                genreString.append(" | ");
                            }
                        }
                    } else {

                        String[] genre = movieOmdb.getGenre().split(", ");
                        int s = genre.length;
                        for (String gen : genre) {
                            genreString.append(gen);
                            s--;
                            if (s > 0) {
                                genreString.append(" | ");
                            }
                        }
                    }
                    movie.setGenres(genreString.toString());

                    movies.add(movie);
                    ++pos[0];

                    if (pos[0] == 20) {

                        Collections.sort(movies, new Comparator<Movie>() {
                            @Override
                            public int compare(Movie o1, Movie o2) {
                                return o2.getRating().compareTo(o1.getRating());
                            }
                        });
                        moviesListener.onFinished(movies);
                    }
                }

                @Override
                public void onFailure(Call<MovieOmdb> call, Throwable t) {
                    moviesListener.onFailure(t);
                    Log.v("TAG_TAG", t.getLocalizedMessage());
                }
            });
        }
    }
}

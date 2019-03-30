package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class MovieDetailsModel implements MovieDetailsContract.Model {

    private OnMovieDetailsListener listener;
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);

    @Override
    public void getMovieDetails(final OnMovieDetailsListener listener, Integer id) {
        this.listener = listener;

        Call<MovieTmdb> call = apiServiceTmdb.getMovieDetail(id, API_KEY_TMDB, "videos,credits");

        call.enqueue(new Callback<MovieTmdb>() {
            @Override
            public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {
                if (response.isSuccessful()) {
                    getMovieDetailsOmdb(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieTmdb> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    private void getMovieDetailsOmdb(final MovieTmdb movieTmdb) {

        Call<MovieOmdb> call = apiServiceOmdb.getMovieDetailOmdb(movieTmdb.getImdb(), API_KEY_OMDB);

        final Movie movie = new Movie();

        if (movieTmdb.getImdb() == null) {
            movie.setPosterPath(movieTmdb.getPosterPath());
            movie.setOverview(movieTmdb.getOverview());
            movie.setId(String.valueOf(movieTmdb.getId()));
            movie.setTitle(movieTmdb.getTitle());
            movie.setBackdropPath(movieTmdb.getBackdropPath());
            movie.setVotes(String.valueOf(movieTmdb.getVoteAverage()));
            movie.setRating(String.valueOf(movieTmdb.getVoteCount()));
            movie.setImdb(movieTmdb.getImdb());

            StringBuilder genreString = new StringBuilder();
            int s = movieTmdb.getGenres().size();
            for (Genre genre : movieTmdb.getGenres()) {
                genreString.append(genre.getName());
                s--;
                if (s > 0) {
                    genreString.append(" | ");
                }
            }

            movie.setGenres(genreString.toString());
            movie.setRuntime(String.valueOf(movieTmdb.getVideoResponse()));
            movie.setVideos(movieTmdb.getVideoResponse().getVideos());
        }

        call.enqueue(new Callback<MovieOmdb>() {
            @Override
            public void onResponse(Call<MovieOmdb> call, Response<MovieOmdb> response) {

                MovieOmdb movieOmdb = response.body();

                movie.setPosterPath(movieTmdb.getPosterPath());
                movie.setOverview(movieOmdb.getPlot() == null || movieOmdb.getPlot().equals("N/A")
                        ? movieTmdb.getOverview()
                        : movieOmdb.getPlot());
                movie.setId(String.valueOf(movieTmdb.getId()));
                movie.setTitle(movieTmdb.getTitle());
                movie.setBackdropPath(movieTmdb.getBackdropPath());

                // TODO: 3/30/19 votes need to be seperated by commas

                movie.setVotes(movieOmdb.getImdbVotes() == null || movieOmdb.getImdbVotes().equals("N/A")
                        ? String.valueOf(movieTmdb.getVoteCount())
                        : movieOmdb.getImdbVotes());
                movie.setRating(movieOmdb.getImdbRating() == null || movieOmdb.getImdbRating().equals("N/A")
                        ? String.valueOf(movieTmdb.getVoteAverage())
                        : movieOmdb.getImdbRating());
                movie.setImdb(movieTmdb.getImdb());

                StringBuilder genreString = new StringBuilder();
                int s = movieTmdb.getGenres().size();
                for (Genre genre : movieTmdb.getGenres()) {
                    genreString.append(genre.getName());
                    s--;
                    if (s > 0) {
                        genreString.append(" | ");
                    }
                }

                movie.setGenres(genreString.toString());

                // TODO: 3/30/19 runtime should be modular

                movie.setRuntime(String.valueOf(movieTmdb.getVideoResponse()));
                movie.setVideos(movieTmdb.getVideoResponse().getVideos());
            }

            @Override
            public void onFailure(Call<MovieOmdb> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}

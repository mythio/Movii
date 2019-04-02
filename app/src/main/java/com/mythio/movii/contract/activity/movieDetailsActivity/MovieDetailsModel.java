package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.Collection.CollectionResponse;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class MovieDetailsModel implements MovieDetailsContract.Model {

    private OnCollectionListener listener;
    private ArrayList<Movie> movies = new ArrayList<>();
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
    private int collectionSize = 1;
    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);

    @Override
    public void getDetails(final OnCollectionListener listener, final Integer id) {
        this.listener = listener;

        Call<MovieTmdb> call = apiServiceTmdb.getMovieDetail(Integer.valueOf(id), API_KEY_TMDB, "");
        call.enqueue(new Callback<MovieTmdb>() {
            @Override
            public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {
                if (response.body().getCollection() != null) {
                    getCollection(String.valueOf(response.body().getCollection().getId()));
                } else {
                    getMovieDetailsTmdb(String.valueOf(id));
                }
            }

            @Override
            public void onFailure(Call<MovieTmdb> call, Throwable t) {

            }
        });
    }

    private void getCollection(String id) {

        Call<CollectionResponse> call = apiServiceTmdb.getCollectionResults(id, API_KEY_TMDB);

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                for (MovieTmdb movie : response.body().getMovies()) {
                    collectionSize = response.body().getMovies().size();
                    getMovieDetailsTmdb(String.valueOf(movie.getId()));
                }
            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {

            }
        });
    }

    private void getMovieDetailsTmdb(String id) {

        Call<MovieTmdb> call = apiServiceTmdb.getMovieDetail(Integer.valueOf(id), API_KEY_TMDB, "videos,credits,recommended");
        call.enqueue(new Callback<MovieTmdb>() {
            @Override
            public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {
                if (response.body().getImdb() == null) {
                    setTmdb(response.body());
                } else {
                    getMovieDetailsOmdb(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieTmdb> call, Throwable t) {

            }
        });
    }

    private void setTmdb(MovieTmdb movieTmdb) {

        Movie movie = new Movie();
        movie.setPosterPath(movieTmdb.getPosterPath());
        movie.setOverview(movieTmdb.getOverview());
        movie.setId(String.valueOf(movieTmdb.getId()));
        movie.setTitle(movieTmdb.getTitle());
        movie.setBackdropPath(movieTmdb.getBackdropPath());
        movie.setVotes(String.valueOf(movieTmdb.getVoteAverage()));
        movie.setRating(String.valueOf(movieTmdb.getVoteCount()));
        movie.setImdb(movieTmdb.getImdb());

        if (movieTmdb.getCollection() != null) {
            movie.setCollectionId(String.valueOf(movieTmdb.getCollection().getId()));
            movie.setCollectionName(movieTmdb.getCollection().getName());
            movie.setCollectionPosterPath(movieTmdb.getCollection().getPosterPath());
            movie.setCollectionBackdrop(movieTmdb.getCollection().getBackdropPath());
        }

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

        movies.add(movie);

        if (collectionSize == movies.size()) {
            listener.onFinished(movies);
        }
    }

    private void getMovieDetailsOmdb(final MovieTmdb movieTmdb) {

        final Movie movie = new Movie();

        Call<MovieOmdb> call = apiServiceOmdb.getMovieDetailOmdb(API_KEY_OMDB, movieTmdb.getImdb());

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

                if (movieTmdb.getCollection() != null) {
                    movie.setCollectionId(String.valueOf(movieTmdb.getCollection().getId()));
                    movie.setCollectionName(movieTmdb.getCollection().getName());
                    movie.setCollectionPosterPath(movieTmdb.getCollection().getPosterPath());
                    movie.setCollectionBackdrop(movieTmdb.getCollection().getBackdropPath());
                }

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

                movies.add(movie);

                // TODO: 4/2/19 sort the movies in the chronological order

                if (collectionSize == movies.size()) {
                    listener.onFinished(movies);
//                    Collections.sort(movies, new Comparator<Movie>() {
//                        @Override
//                        public int compare(Movie o1, Movie o2) {
//                            return 0;
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<MovieOmdb> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
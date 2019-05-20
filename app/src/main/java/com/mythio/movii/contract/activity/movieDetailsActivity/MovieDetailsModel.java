package com.mythio.movii.contract.activity.movieDetailsActivity;

import com.mythio.movii.model.collection.CollectionResponse;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

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
                if (response.isSuccessful()) {
                    if (response.body().getCollection() != null) {
                        getCollection(String.valueOf(response.body().getCollection().getId()));
                    } else {
                        getMovieDetailsTmdb(String.valueOf(id));
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieTmdb> call, Throwable t) {
                listener.onFailure(t.getMessage());
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
                listener.onFailure(t.getMessage());
            }
        });
    }

    private void getMovieDetailsTmdb(String id) {

        Call<MovieTmdb> call = apiServiceTmdb.getMovieDetail(Integer.valueOf(id), API_KEY_TMDB, "videos,credits,recommendations");
        call.enqueue(new Callback<MovieTmdb>() {
            @Override
            public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {
                if (response.isSuccessful()) {
                    if (response.body().getImdb() == null) {
                        setTmdb(response.body());
                    } else {
                        getMovieDetailsOmdb(response.body());
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieTmdb> call, Throwable t) {
                listener.onFailure(t.getMessage());
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
        movie.setRating(String.valueOf(movieTmdb.getVoteAverage()));
        movie.setImdb(movieTmdb.getImdb());
        movie.setCasts(movieTmdb.getCredits().getCast());

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

        String[] date = movieTmdb.getReleaseDate().split("-");
        movie.setYear(date[0]);

        movie.setGenres(genreString.toString());

        int runtime = movieTmdb.getRuntime();

        movie.setVotes(String.valueOf(NumberFormat.getInstance(Locale.US).format(movieTmdb.getVoteCount())));
        movie.setRuntime(runtime / 60 + " h " + runtime % 60 + " m");
        movie.setVideos(movieTmdb.getVideoResponse().getVideos());
        movie.setMoviesTmdb(movieTmdb.getRecommendations().getResults());
        movies.add(movie);

        if (collectionSize == movies.size()) {
            Collections.sort(movies, (o1, o2) -> o1.getYear().compareTo(o2.getYear()));
            listener.onFinished(movies);
        }
    }

    private void getMovieDetailsOmdb(final MovieTmdb movieTmdb) {

        final Movie movie = new Movie();
        Call<MovieOmdb> call = apiServiceOmdb.getMovieDetailOmdb(API_KEY_OMDB, movieTmdb.getImdb());
        call.enqueue(new Callback<MovieOmdb>() {
            @Override
            public void onResponse(Call<MovieOmdb> call, Response<MovieOmdb> response) {
                if (response.isSuccessful()) {
                    MovieOmdb movieOmdb = response.body();

                    movie.setPosterPath(movieTmdb.getPosterPath());
                    movie.setOverview(movieOmdb.getPlot() == null || movieOmdb.getPlot().equals("N/A")
                            ? movieTmdb.getOverview()
                            : movieOmdb.getPlot());
                    movie.setId(String.valueOf(movieTmdb.getId()));
                    movie.setTitle(movieTmdb.getTitle());
                    movie.setBackdropPath(movieTmdb.getBackdropPath());

                    movie.setVotes(movieOmdb.getImdbVotes() == null || movieOmdb.getImdbVotes().equals("N/A")
                            ? String.valueOf(NumberFormat.getInstance(Locale.US).format(movieTmdb.getVoteCount()))
                            : movieOmdb.getImdbVotes());
                    movie.setRating(movieOmdb.getImdbRating() == null || movieOmdb.getImdbRating().equals("N/A")
                            ? String.valueOf(movieTmdb.getVoteAverage())
                            : movieOmdb.getImdbRating());
                    movie.setImdb(movieTmdb.getImdb());
                    movie.setCasts(movieTmdb.getCredits().getCast());
                    movie.setMoviesTmdb(movieTmdb.getRecommendations().getResults());

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

                    String[] date = movieTmdb.getReleaseDate().split("-");
                    movie.setYear(date[0]);

                    movie.setGenres(genreString.toString());

                    int runtime;
                    if (movieTmdb.getRuntime() != null) {
                        runtime = movieTmdb.getRuntime();
                    } else {
                        runtime = 0;
                    }

                    movie.setRuntime(runtime / 60 + " h " + runtime % 60 + " m");
                    movie.setVideos(movieTmdb.getVideoResponse().getVideos());

                    movies.add(movie);

                    if (collectionSize == movies.size()) {
                        Collections.sort(movies, (o1, o2) -> o1.getYear().compareTo(o2.getYear()));
                        listener.onFinished(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieOmdb> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
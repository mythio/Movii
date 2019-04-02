package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShow;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class TvShowDetailsModel implements TvShowDetailsContract.Model {

    private OnTvShowDetailsListener listener;
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);

    @Override
    public void getTvShowDetails(final OnTvShowDetailsListener listener, Integer id) {
        this.listener = listener;

        Call<TvShowTmdb> call = apiServiceTmdb.getTvShowDetail(id, API_KEY_TMDB, "external_ids,videos,credits");

        call.enqueue(new Callback<TvShowTmdb>() {
            @Override
            public void onResponse(Call<TvShowTmdb> call, Response<TvShowTmdb> response) {
                if (response.isSuccessful()) {
                    if (response.body().getExternalIds().getImdbId() == null) {
//                        setTmdb(response.body());
                    } else {
//                        getMovieDetailsOmdb(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowTmdb> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

//    private void setTmdb(TvShowTmdb tvShowTmdb) {
//
//        TvShow tvShow = new TvShow();
//
//        tvShow.setBackdropPath(tvShowTmdb.getBackdropPath());
//
//        StringBuilder genreString = new StringBuilder();
//        int s = movieTmdb.getGenres().size();
//        for (Genre genre : movieTmdb.getGenres()) {
//            genreString.append(genre.getName());
//            s--;
//            if (s > 0) {
//                genreString.append(" | ");
//            }
//        }
//
//        movie.setGenres(genreString.toString());
//        movie.setRuntime(String.valueOf(movieTmdb.getVideoResponse()));
//        movie.setVideos(movieTmdb.getVideoResponse().getVideos());
//
//        listener.onFinished(movie);
//    }
//
//    private void getMovieDetailsOmdb(final MovieTmdb movieTmdb) {
//
//        final Movie movie = new Movie();
//
//        Call<MovieOmdb> call = apiServiceOmdb.getMovieDetailOmdb(API_KEY_OMDB, movieTmdb.getImdb());
//
//        call.enqueue(new Callback<MovieOmdb>() {
//            @Override
//            public void onResponse(Call<MovieOmdb> call, Response<MovieOmdb> response) {
//
//                MovieOmdb movieOmdb = response.body();
//
//                movie.setPosterPath(movieTmdb.getPosterPath());
//                movie.setOverview(movieOmdb.getPlot() == null || movieOmdb.getPlot().equals("N/A")
//                        ? movieTmdb.getOverview()
//                        : movieOmdb.getPlot());
//                movie.setId(String.valueOf(movieTmdb.getId()));
//                movie.setTitle(movieTmdb.getTitle());
//                movie.setBackdropPath(movieTmdb.getBackdropPath());
//
//                // TODO: 3/30/19 votes need to be seperated by commas
//
//                movie.setVotes(movieOmdb.getImdbVotes() == null || movieOmdb.getImdbVotes().equals("N/A")
//                        ? String.valueOf(movieTmdb.getVoteCount())
//                        : movieOmdb.getImdbVotes());
//                movie.setRating(movieOmdb.getImdbRating() == null || movieOmdb.getImdbRating().equals("N/A")
//                        ? String.valueOf(movieTmdb.getVoteAverage())
//                        : movieOmdb.getImdbRating());
//                movie.setImdb(movieTmdb.getImdb());
//
//                StringBuilder genreString = new StringBuilder();
//                int s = movieTmdb.getGenres().size();
//                for (Genre genre : movieTmdb.getGenres()) {
//                    genreString.append(genre.getName());
//                    s--;
//                    if (s > 0) {
//                        genreString.append(" | ");
//                    }
//                }
//
//                movie.setGenres(genreString.toString());
//
//                // TODO: 3/30/19 runtime should be modular
//
//                movie.setRuntime(String.valueOf(movieTmdb.getVideoResponse()));
//                movie.setVideos(movieTmdb.getVideoResponse().getVideos());
//
//                listener.onFinished(movie);
//            }
//
//            @Override
//            public void onFailure(Call<MovieOmdb> call, Throwable t) {
//                listener.onFailure(t);
//            }
//        });
//    }
}

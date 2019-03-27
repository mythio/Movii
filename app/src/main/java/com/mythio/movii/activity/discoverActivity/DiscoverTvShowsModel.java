package com.mythio.movii.activity.discoverActivity;

import android.util.Log;

import com.mythio.movii.activity.discoverActivity.DiscoverContract.Model;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.tvShow.TvShow;
import com.mythio.movii.model.tvShow.TvShowOmdb;
import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.model.tvShow.TvShowTmdb;
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

public class DiscoverTvShowsModel implements Model.TvShowsModel {

    private TvShowsListener tvShowsListener;
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);
    private ArrayList<TvShowTmdb> tvShowTmdbArrayList = new ArrayList<>();
    private ArrayList<TvShow> tvShows = new ArrayList<>();

    /*
    TV SHOW FRAGMENT DATA
     */

    @Override
    public void getTvShows(final TvShowsListener tvShowsListener) {

        this.tvShowsListener = tvShowsListener;

        Call<TvShowResponse> call = apiServiceTmdb.getPopularTvShows(API_KEY_TMDB);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                ArrayList<TvShowTmdb> tvShowTmdbArrayList = response.body().getResults();

                Log.v("TAG_TAG", response.toString());
                getTvTmdb(tvShowTmdbArrayList);
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                tvShowsListener.onFailureTvShows(t);
                Log.v("TAG_TAG", t.getLocalizedMessage());
            }
        });
    }

    private void getTvTmdb(final ArrayList<TvShowTmdb> tvShows) {

        Call<TvShowTmdb> call;
        final int[] pos = {0};
        for (final TvShowTmdb tvShowTmdb : tvShows) {

            call = apiServiceTmdb.getTvShowDetail(tvShowTmdb.getId(), API_KEY_TMDB, "videos,external_ids");
            call.enqueue(new Callback<TvShowTmdb>() {
                @Override
                public void onResponse(Call<TvShowTmdb> call, Response<TvShowTmdb> response) {

                    Log.v("TAG_TAG_TV_TMDB", pos[0] + response.toString());

                    tvShowTmdbArrayList.add(response.body());
                    ++pos[0];
                    if (pos[0] == 20) {
                        getTvShowOmdb();
                    }
                }

                @Override
                public void onFailure(Call<TvShowTmdb> call, Throwable t) {
                    tvShowsListener.onFailureTvShows(t);
                }
            });
        }
    }

    private void getTvShowOmdb() {
        final int[] pos = {0};
        for (final TvShowTmdb tvShowTmdb : tvShowTmdbArrayList) {

            Call<TvShowOmdb> call = apiServiceOmdb.getTvShowDetailOmdb(API_KEY_OMDB, tvShowTmdb.getExternalIds().getImdbId());
            call.enqueue(new Callback<TvShowOmdb>() {
                @Override
                public void onResponse(Call<TvShowOmdb> call, Response<TvShowOmdb> response) {

                    TvShow tvShow = new TvShow();

                    if (tvShowTmdb.getExternalIds().getImdbId() == null) {

                        tvShow.setBackdropPath(tvShowTmdb.getBackdropPath());
                        tvShow.setEpisodeRunTime(tvShowTmdb.getEpisodeRunTime());
                        tvShow.setFirstAirDate(tvShowTmdb.getFirstAirDate());
                        tvShow.setId(String.valueOf(tvShowTmdb.getId()));
                        tvShow.setLastAirDate(tvShowTmdb.getLastAirDate());
                        tvShow.setLastEpisodeToAir(tvShowTmdb.getLastEpisodeToAir());
                        tvShow.setName(tvShowTmdb.getName());
                        tvShow.setNextEpisodeToAir(tvShowTmdb.getNextEpisodeToAir());
                        tvShow.setNumberOfEpisodes(tvShowTmdb.getNumberOfEpisodes());
                        tvShow.setNumberOfSeasons(String.valueOf(tvShowTmdb.getNumberOfSeasons()));
                        tvShow.setOverview(tvShowTmdb.getOverview());
                        tvShow.setPosterPath(tvShowTmdb.getPosterPath());
                        tvShow.setSeasons(tvShowTmdb.getSeasons());
                        tvShow.setStatus(tvShowTmdb.getStatus());
                        tvShow.setType(tvShowTmdb.getType());
                        tvShow.setVideos(tvShowTmdb.getVideoResponse().getVideos());
                        tvShow.setImdb(tvShowTmdb.getExternalIds().getImdbId());
                        tvShow.setRating(String.valueOf(tvShowTmdb.getVoteAverage()));
                        tvShow.setVotes(String.valueOf(tvShowTmdb.getVoteCount()));

                        StringBuilder genreString = new StringBuilder();
                        int s = tvShowTmdb.getGenres().size();
                        for (Genre genre : tvShowTmdb.getGenres()) {
                            genreString.append(genre.getName());
                            s--;
                            if (s > 0) {
                                genreString.append(" | ");
                            }
                        }
                        tvShow.setGenres(genreString.toString());
                    } else {
                        TvShowOmdb tvShowOmdb = response.body();

                        tvShow.setBackdropPath(tvShowTmdb.getBackdropPath());
                        tvShow.setEpisodeRunTime(tvShowTmdb.getEpisodeRunTime());
                        tvShow.setFirstAirDate(tvShowTmdb.getFirstAirDate());
                        tvShow.setId(String.valueOf(tvShowTmdb.getId()));
                        tvShow.setLastAirDate(tvShowTmdb.getLastAirDate());
                        tvShow.setLastEpisodeToAir(tvShowTmdb.getLastEpisodeToAir());
                        tvShow.setName(tvShowTmdb.getName());
                        tvShow.setNextEpisodeToAir(tvShowTmdb.getNextEpisodeToAir());
                        tvShow.setNumberOfEpisodes(tvShowTmdb.getNumberOfEpisodes());
                        tvShow.setNumberOfSeasons(tvShowOmdb.getTotalSeasons());
                        tvShow.setOverview(tvShowOmdb.getPlot() == null ? tvShowTmdb.getOverview() : tvShowOmdb.getPlot());
                        tvShow.setPosterPath(tvShowTmdb.getPosterPath());
                        tvShow.setSeasons(tvShowTmdb.getSeasons());
                        tvShow.setStatus(tvShowTmdb.getStatus());
                        tvShow.setType(tvShowTmdb.getType());
                        tvShow.setVideos(tvShowTmdb.getVideoResponse().getVideos());
                        tvShow.setImdb(tvShowTmdb.getExternalIds().getImdbId());
                        tvShow.setRating(tvShowOmdb.getImdbRating() == null || tvShowOmdb.getImdbRating().equals("N/A") ? String.valueOf(tvShowTmdb.getVoteAverage()) : tvShowOmdb.getImdbRating());
                        tvShow.setVotes(tvShowOmdb.getImdbVotes() == null || tvShowOmdb.getImdbVotes().equals("N/A") ? String.valueOf(tvShowTmdb.getVoteCount()) : tvShowOmdb.getImdbVotes());

                        StringBuilder genreString = new StringBuilder();
                        if (tvShowOmdb.getGenre() == null || tvShowOmdb.getGenre().equals("N/A")) {

                            int s = tvShowTmdb.getGenres().size();
                            for (Genre genre : tvShowTmdb.getGenres()) {
                                genreString.append(genre.getName());
                                s--;
                                if (s > 0) {
                                    genreString.append(" | ");
                                }
                            }
                        } else {

                            String[] genre = tvShowOmdb.getGenre().split(", ");
                            int s = genre.length;
                            for (String gen : genre) {
                                genreString.append(gen);
                                s--;
                                if (s > 0) {
                                    genreString.append(" | ");
                                }
                            }
                        }
                        tvShow.setGenres(genreString.toString());
                    }
                    tvShows.add(tvShow);
                    ++pos[0];

                    if (pos[0] == 20) {

                        Collections.sort(tvShows, new Comparator<TvShow>() {
                            @Override
                            public int compare(TvShow o1, TvShow o2) {
                                return o2.getRating().compareTo(o1.getRating());
                            }
                        });
                        tvShowsListener.onFinishedTvShows(tvShows);
                    }
                }

                @Override
                public void onFailure(Call<TvShowOmdb> call, Throwable t) {
                    tvShowsListener.onFailureTvShows(t);
                    Log.v("TAG_TAG", t.getLocalizedMessage());
                }
            });
        }
    }
}

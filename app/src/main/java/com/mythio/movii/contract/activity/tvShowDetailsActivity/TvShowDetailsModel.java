package com.mythio.movii.contract.activity.tvShowDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.season.SeasonDetails;
import com.mythio.movii.model.tvShow.TvShow;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class TvShowDetailsModel implements TvShowDetailsContract.Model {

    private OnTvShowDetailsListener listener;
    private TvShow tvShow;
    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);
//    private EndPointsOmdb apiServiceOmdb = ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class);

    @Override
    public void getTvShowDetails(final OnTvShowDetailsListener listener, Integer id) {
        this.listener = listener;

        Call<TvShowTmdb> call = apiServiceTmdb.getTvShowDetail(id, API_KEY_TMDB, "external_ids,season/1,season/2,season/3,season/4,season/5,season/6,season/7,season/8,season/9,season/10,season/11,,season/12,season/13,season/14");

        call.enqueue(new Callback<TvShowTmdb>() {
            @Override
            public void onResponse(Call<TvShowTmdb> call, Response<TvShowTmdb> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getExternalIds().getImdbId() != null) {
//                        getTvShowDetailsOmdb(response.body().getExternalIds().getImdbId());
//                    } else {
                    setTmdb(response.body());
//                    }
//                    Log.v("TAG_TAG", response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<TvShowTmdb> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    private void setTmdb(TvShowTmdb tvShowTmdb) {

        ArrayList<SeasonDetails> seasons = new ArrayList<>();

        tvShow = new TvShow();
        tvShow.setBackdropPath(tvShowTmdb.getBackdropPath());
        tvShow.setId(String.valueOf(tvShowTmdb.getId()));
        tvShow.setName(tvShowTmdb.getName());
        tvShow.setOverview(tvShowTmdb.getOverview());
        tvShow.setPosterPath(tvShowTmdb.getPosterPath());
        tvShow.setImdb(tvShowTmdb.getExternalIds().getImdbId());
        tvShow.setVotes(String.valueOf(NumberFormat.getInstance(Locale.US).format(tvShowTmdb.getVoteCount())));
        tvShow.setRating(String.valueOf(tvShowTmdb.getVoteAverage()));

        StringBuilder genreString = new StringBuilder();
        int s = tvShowTmdb.getGenres().size();
        for (Genre genre : tvShowTmdb.getGenres()) {
            genreString.append(genre.getName());
            s--;
            if (s > 0) {
                genreString.append(" | ");
            }
        }

        switch (tvShowTmdb.getNumberOfSeasons()) {
            case 17:
                seasons.add(tvShowTmdb.getSeason17());
            case 16:
                seasons.add(tvShowTmdb.getSeason16());
            case 15:
                seasons.add(tvShowTmdb.getSeason15());
            case 14:
                seasons.add(tvShowTmdb.getSeason14());
            case 13:
                seasons.add(tvShowTmdb.getSeason13());
            case 12:
                seasons.add(tvShowTmdb.getSeason12());
            case 11:
                seasons.add(tvShowTmdb.getSeason11());
            case 10:
                seasons.add(tvShowTmdb.getSeason10());
            case 9:
                seasons.add(tvShowTmdb.getSeason9());
            case 8:
                seasons.add(tvShowTmdb.getSeason8());
            case 7:
                seasons.add(tvShowTmdb.getSeason7());
            case 6:
                seasons.add(tvShowTmdb.getSeason6());
            case 5:
                seasons.add(tvShowTmdb.getSeason5());
            case 4:
                seasons.add(tvShowTmdb.getSeason4());
            case 3:
                seasons.add(tvShowTmdb.getSeason3());
            case 2:
                seasons.add(tvShowTmdb.getSeason2());
            case 1:
                seasons.add(tvShowTmdb.getSeason1());
        }

        Log.d("TAG_TAG", tvShowTmdb.getSeason1().getName());

        tvShow.setSeasons(seasons);

        for (SeasonDetails details : tvShow.getSeasons()) {
            Log.d("TAG_TAG", details.getName() + " " + details.getSeasonNumber());
        }

        tvShow.setGenres(genreString.toString());

        listener.onFinished(tvShow);
    }

    private void getTvShowDetailsOmdb(String imdb) {

    }
}

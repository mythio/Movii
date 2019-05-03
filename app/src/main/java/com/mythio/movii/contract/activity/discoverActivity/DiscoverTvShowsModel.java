package com.mythio.movii.contract.activity.discoverActivity;

import com.mythio.movii.contract.activity.discoverActivity.DiscoverContract.Model;
import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverTvShowsModel implements Model.TvShowsModel {

    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);

    /*
    TV SHOW FRAGMENT DATA
     */

    @Override
    public void getTvShows(final TvShowsListener tvShowsListener) {

        Call<TvShowResponse> call = apiServiceTmdb.getPopularTvShows(API_KEY_TMDB);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    tvShowsListener.onFinishedTvShows(response.body().getResults());
                } else {
                    tvShowsListener.onFailureTvShows(response.message());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                tvShowsListener.onFailureTvShows(t.getMessage());
            }
        });
    }
}

package com.mythio.movii.activity.SearchTvShow.contract;

import androidx.annotation.NonNull;

import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class SearchTvShowModel implements SearchTvShowContract.Model {

    private EndPointTmdb apiServiceTmdb = ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class);

    @Override
    public void getSearchResults(final OnTvShowSearchListener listener, String query) {

        Call<TvShowResponse> call = apiServiceTmdb.getTvShowSearchResults(API_KEY_TMDB, query);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.body() != null) {
                    listener.onFinished(response.body().getResults());
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}

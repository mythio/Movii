package com.mythio.movii.activity.SearchTvShow.contract;

import android.util.Log;

import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class SearchTvShowModel implements SearchTvShowContract.Model {

    private static final String TAG = "movii.debug: SearchTvShowModel";

    @Override
    public void getSearchResults(final OnTvShowSearchListener listener, String query) {

        getSingleSearch(query)
                .map(TvShowResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<TvShowTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<TvShowTmdb> tvShowTmdbs) {
                        listener.onFinished(tvShowTmdbs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    private Single<TvShowResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getTvShowSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

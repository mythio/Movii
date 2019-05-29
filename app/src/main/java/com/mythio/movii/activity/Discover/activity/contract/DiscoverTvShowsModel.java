package com.mythio.movii.activity.Discover.activity.contract;

import android.util.Log;

import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract.Model;
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

public class DiscoverTvShowsModel implements Model.TvShowsModel {

    private static final String TAG = "movii.debug: DiscoverTvShowsModel";

    @Override
    public void getTvShows(final TvShowsListener listener) {

        getSinglePopularTvShows()
                .map(TvShowResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<TvShowTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<TvShowTmdb> tvShowTmdbs) {
                        listener.onFinishedTvShows(tvShowTmdbs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    private Single<TvShowResponse> getSinglePopularTvShows() {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getPopularTvShows(API_KEY_TMDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

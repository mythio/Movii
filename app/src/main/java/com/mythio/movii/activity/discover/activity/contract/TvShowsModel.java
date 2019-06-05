package com.mythio.movii.activity.discover.activity.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.activity.discover.activity.contract.Contract.Model;
import com.mythio.movii.model.tv_show.TvShowResponse;
import com.mythio.movii.model.tv_show.TvShowTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class TvShowsModel implements Model.TvShowsModel {

    private static final String TAG = "movii.debug: TvShowsModel";

    @Override
    public void getTvShows(@NonNull final TvShowsListener listener) {

        getSinglePopularTvShows()
                .map(TvShowResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<TvShowTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<TvShowTmdb> tvShowTmdbs) {
                        listener.onFinishedTvShows(tvShowTmdbs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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

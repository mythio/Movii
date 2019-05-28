package com.mythio.movii.activity.Discover.activity.contract;

import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract.Model;
import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverTvShowsModel implements Model.TvShowsModel {

    private static final String TAG = "DiscoverTvShowsModel";

    @Override
    public void getTvShows(final TvShowsListener listener) {

        getSinglePopularTvShows()
                .subscribe(new DisposableSingleObserver<TvShowResponse>() {
                    @Override
                    public void onSuccess(TvShowResponse response) {
                        listener.onFinishedTvShows(response.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

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

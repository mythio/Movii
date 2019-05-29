package com.mythio.movii.activity.SearchTvShow.contract;

import android.util.Log;

import com.mythio.movii.model.tvShow.TvShowResponse;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class Model implements Contract.Model {

    private static final String TAG = "movii.debug: Model";

    @Override
    public void getSearchResults(final OnTvShowSearchListener listener, String query) {

        getSingleSearch(query)
                .debounce(150, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(TvShowResponse::getResults)
                .switchMap((Function<ArrayList<TvShowTmdb>, ObservableSource<ArrayList<TvShowTmdb>>>)
                        Observable::just)
                .subscribe(new DisposableObserver<ArrayList<TvShowTmdb>>() {
                    @Override
                    public void onNext(ArrayList<TvShowTmdb> tvShowTmdbs) {
                        listener.onFinished(tvShowTmdbs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<TvShowResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getTvShowSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

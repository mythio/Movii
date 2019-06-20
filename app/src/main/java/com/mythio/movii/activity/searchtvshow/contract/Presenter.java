package com.mythio.movii.activity.searchtvshow.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.tvshow.TvShow;
import com.mythio.movii.model.tvshow.TvShowResponse;
import com.mythio.movii.network.ApiTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constants.API_KEY_TMDB;

public class Presenter implements Contract.Presenter {

    private static final String TAG = "TAG_TAG_TAG: Presenter";

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String query) {
        getSingleSearch(query)
                .debounce(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(TvShowResponse::getResults)
                .switchMap((Function<ArrayList<TvShow>, ObservableSource<ArrayList<TvShow>>>)
                        Observable::just)
                .subscribe(new DisposableObserver<ArrayList<TvShow>>() {
                    @Override
                    public void onNext(ArrayList<TvShow> tvShows) {
                        view.showSearchResult(tvShows);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {
                        /*
                        Not used
                         */
                    }
                });
        view.hidePlate();
    }

    @Override
    public void detachView() {
        view = null;
    }

    private Observable<TvShowResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(ApiTmdb.class)
                .getTvShowSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
package com.mythio.movii.activity.search_tv_show.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.tv_show.TvShow;
import com.mythio.movii.model.tv_show.TvShowResponse;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY;

public class Presenter implements Contract.Presenter {

    private static final String TAG = "Presenter";

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
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

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
                .getClient()
                .create(EndPoint.class)
                .getTvShowSearchResults(API_KEY, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
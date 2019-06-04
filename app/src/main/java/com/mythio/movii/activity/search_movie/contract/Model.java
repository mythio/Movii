package com.mythio.movii.activity.search_movie.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
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
    private int s = -1;

    @Override
    public void getSearchResults(@NonNull final OnMoviesSearchListener listener, String query) {

        getSingleSearch(query)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(MovieResponse::getResults)
//                .filter(movieTmdbs -> {
//                    if (s == movieTmdbs.size()) {
//                        return false;
//                    } else {
//                        s = movieTmdbs.size();
//                        return true;
//                    }
//                })
                .switchMap((Function<ArrayList<MovieTmdb>, ObservableSource<ArrayList<MovieTmdb>>>)
                        Observable::just)
                .distinctUntilChanged()
                .subscribe(new DisposableObserver<ArrayList<MovieTmdb>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<MovieTmdb> movieTmdbs) {
                        Log.d(TAG, "onNext: " + movieTmdbs.size());
                        listener.onFinished(movieTmdbs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<MovieResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getMovieSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

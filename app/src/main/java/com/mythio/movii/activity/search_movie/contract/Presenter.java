package com.mythio.movii.activity.search_movie.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.ApiTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .map(MovieResponse::getResults)
                .distinctUntilChanged()
                .subscribe(new DisposableObserver<ArrayList<Movie>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<Movie> movies) {
                        view.showSearchResult(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });

        view.hidePlate();
    }

    @Override
    public void detachView() {
        view = null;
    }

    private Observable<MovieResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(ApiTmdb.class)
                .getMovieSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

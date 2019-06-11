package com.mythio.movii.activity.search_movie.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY;

public class Model implements Contract.Model {

    private static final String TAG = "movii.debug: Model";

    @Override
    public void getSearchResults(@NonNull final OnSearchResultsListener listener, String query) {

        getSingleSearch(query)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(MovieResponse::getResults)
//                .switchMap((Function<ArrayList<Movie>, ObservableSource<ArrayList<Movie>>>)
//                        Observable::just)
                .distinctUntilChanged()
                .subscribe(new DisposableObserver<ArrayList<Movie>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<Movie> movies) {
                        Log.d(TAG, "onNext: " + movies.size());
                        listener.onFinished(movies);
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
                .getClient()
                .create(EndPoint.class)
                .getMovieSearchResults(API_KEY, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

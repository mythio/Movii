package com.mythio.movii.activity.SearchMovie.contract;

import android.util.Log;

import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class SearchMovieModel implements SearchMovieContract.Model {

    private static final String TAG = "movii.debug: SearchMovieModel";

    @Override
    public void getSearchResults(final OnMoviesSearchListener listener, String query) {

        getSingleSearch(query)
                .map(MovieResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<MovieTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<MovieTmdb> movieTmdbs) {
                        listener.onFinished(movieTmdbs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    private Single<MovieResponse> getSingleSearch(String query) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getMovieSearchResults(API_KEY_TMDB, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

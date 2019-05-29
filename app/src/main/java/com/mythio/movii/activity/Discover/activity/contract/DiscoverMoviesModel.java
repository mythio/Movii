package com.mythio.movii.activity.Discover.activity.contract;

import android.util.Log;

import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract.Model;
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

public class DiscoverMoviesModel implements Model.MoviesModel {

    private static final String TAG = "movii.debug: DiscoverMoviesModel";

    @Override
    public void getMovies(final MoviesListener listener) {

        getSinglePopularMovies()
                .map(MovieResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<MovieTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<MovieTmdb> movieTmdbs) {
                        listener.onFinishedMovies(movieTmdbs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    private Single<MovieResponse> getSinglePopularMovies() {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getPopularMovies(API_KEY_TMDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
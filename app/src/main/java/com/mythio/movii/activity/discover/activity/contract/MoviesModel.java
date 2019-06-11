package com.mythio.movii.activity.discover.activity.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.activity.discover.activity.contract.Contract.Model;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY;

public class MoviesModel implements Model.MoviesModel {

    private static final String TAG = "movii.debug: MoviesModel";

    @Override
    public void getMovies(@NonNull final MoviesListener listener) {

        getSinglePopularMovies()
                .map(MovieResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<MovieTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<MovieTmdb> movieTmdbs) {
                        Log.d(TAG, "onSuccess: ");
                        listener.onFinishedMovies(movieTmdbs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    private Single<MovieResponse> getSinglePopularMovies() {
        return RetrofitBuilder
                .getClient()
                .create(EndPoint.class)
                .getPopularMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
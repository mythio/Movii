package com.mythio.movii.activity.movie_details.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;
import com.mythio.movii.network.RetrofitBuilder;
import com.mythio.movii.util.Utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class Model implements Contract.Model {

    private static final String TAG = "movii.debug: Model";

    @Override
    public void getDetails(@NonNull MovieDetailsListener listener, int id) {

        getMovieTmdbObservable(id)
                .flatMap((Function<MovieTmdb, ObservableSource<MovieOmdb>>)
                        movieTmdb -> getMovieOmdbObservable(movieTmdb.getImdb()), Utils::getMovie)
                .subscribe(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        listener.onFinished(movie);
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

    private Observable<MovieTmdb> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getMovieDetail(id, API_KEY_TMDB, "recommendations,videos,credits")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<MovieOmdb> getMovieOmdbObservable(String id) {
        return RetrofitBuilder
                .getClientOmdb()
                .create(EndPointsOmdb.class)
                .getMovieDetail(API_KEY_OMDB, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
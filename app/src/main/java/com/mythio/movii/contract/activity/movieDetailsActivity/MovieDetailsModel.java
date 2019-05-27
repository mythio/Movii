package com.mythio.movii.contract.activity.movieDetailsActivity;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientBuilderOmdb;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.EndPointsOmdb;
import com.mythio.movii.util.Parse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_OMDB;
import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class MovieDetailsModel implements MovieDetailsContract.Model {

    private static final String TAG = "movii.debug: MovieDetailsModel";

    @Override
    public void getDetails(OnCollectionListener listener, Integer id) {

        getMovieTmdbObservable(id)
                .toObservable()
                .flatMap((Function<MovieTmdb, ObservableSource<MovieOmdb>>) movieTmdb -> getMovieOmdbObservable(movieTmdb.getImdb()), Parse::getMovie)
                .subscribe(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext()");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Single<MovieTmdb> getMovieTmdbObservable(int id) {
        return ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class)
                .getMovieDetail(id, API_KEY_TMDB, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<MovieOmdb> getMovieOmdbObservable(String id) {
        return ApiClientBuilderOmdb.getClient().create(EndPointsOmdb.class)
                .getMovieDetailOmdb(API_KEY_OMDB, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
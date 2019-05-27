package com.mythio.movii.activity.Discover.activity.contract;

import android.util.Log;

import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract.Model;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.ApiClientBuilderTmdb;
import com.mythio.movii.network.EndPointTmdb;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverMoviesModel implements Model.MoviesModel {

    private static final String TAG = "movii.debug: DiscoverMoviesModel";

    @Override
    public void getMovies(final MoviesListener listener) {

        Single<MovieResponse> single = getSingle();

        single.subscribe(getSubscriber(listener));
    }

    private Single<MovieResponse> getSingle() {
        return ApiClientBuilderTmdb.getClient().create(EndPointTmdb.class)
                .getPopularMovies(API_KEY_TMDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableSingleObserver<MovieResponse> getSubscriber(MoviesListener listener) {
        return new DisposableSingleObserver<MovieResponse>() {
            @Override
            public void onSuccess(MovieResponse response) {
                listener.onFinishedMovies(response.getResults());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
        };
    }
}

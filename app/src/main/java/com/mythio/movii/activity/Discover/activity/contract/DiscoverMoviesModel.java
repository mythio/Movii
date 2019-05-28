package com.mythio.movii.activity.Discover.activity.contract;

import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract.Model;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class DiscoverMoviesModel implements Model.MoviesModel {

    @Override
    public void getMovies(final MoviesListener listener) {

        getSinglePopularMovies()
                .subscribe(new DisposableSingleObserver<MovieResponse>() {
                    @Override
                    public void onSuccess(MovieResponse response) {
                        listener.onFinishedMovies(response.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

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
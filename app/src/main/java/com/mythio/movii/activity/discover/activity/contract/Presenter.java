package com.mythio.movii.activity.discover.activity.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tv_show.TvShowResponse;
import com.mythio.movii.model.tv_show.TvShowTmdb;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY;

public class Presenter implements Contract.Presenter {

    private static final String TAG = "Presenter";

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void setFragment(BaseDiscoverFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void getData() {
        getSinglePopularMovies()
                .map(MovieResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<MovieTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<MovieTmdb> movieTmdbs) {
                        view.sendToMoviesFragment(movieTmdbs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });

        getSinglePopularTvShows()
                .map(TvShowResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<TvShowTmdb>>() {
                    @Override
                    public void onSuccess(ArrayList<TvShowTmdb> tvShowTmdbs) {
                        view.sendToTvShowsFragment(tvShowTmdbs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    private Single<MovieResponse> getSinglePopularMovies() {
        return RetrofitBuilder
                .getClient()
                .create(EndPoint.class)
                .getPopularMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<TvShowResponse> getSinglePopularTvShows() {
        return RetrofitBuilder
                .getClient()
                .create(EndPoint.class)
                .getPopularTvShows(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

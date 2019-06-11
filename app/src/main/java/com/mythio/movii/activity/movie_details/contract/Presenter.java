package com.mythio.movii.activity.movie_details.contract;

import android.util.Log;

import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY;

public class Presenter implements Contract.Presenter {

    private static final String TAG = "TAG_TAG_TAG: Presenter";
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getDetails(int id) {
        getMovieTmdbObservable(id)
                .subscribe(new DisposableSingleObserver<MovieTmdb>() {
                    @Override
                    public void onSuccess(MovieTmdb movieTmdb) {
                        view.showMovieDetails(movieTmdb);
                        view.showCastRecyclerView(movieTmdb.getCredits().getCast());
                        view.showRecommendationsRecyclerView(movieTmdb.getRecommendations().getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    private Single<MovieTmdb> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClient()
                .create(EndPoint.class)
                .getMovieDetail(id, API_KEY, "recommendations,videos,credits")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
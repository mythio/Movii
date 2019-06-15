package com.mythio.movii.activity.movie_details.contract;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.network.ApiTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constants.API_KEY_TMDB;

public class Presenter implements Contract.Presenter {

    private static final String TAG = "TAG_TAG_TAG: Presenter";
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getDetails(int id) {
        getMovieTmdbObservable(id)
                .subscribe(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        view.showMovieDetails(movie);
                        view.showCastRecyclerView(movie.getCredits().getCast());
                        view.showRecommendationsRecyclerView(movie.getRecommendations().getResults());
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

    private Single<Movie> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClient()
                .create(ApiTmdb.class)
                .getMovieDetail(id, API_KEY_TMDB, "recommendations,videos,credits")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
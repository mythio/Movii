package com.mythio.movii.activity.moviedetails.castdialogcontract;

import android.util.Log;

import com.mythio.movii.model.cast.CastMovies;
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
    public void onViewInitialized() {
        view.showInitView();
    }

    @Override
    public void getData(int id) {
        getMovieTmdbObservable(id)
                .subscribe(new DisposableSingleObserver<CastMovies>() {
                    @Override
                    public void onSuccess(CastMovies castMovies) {
                        view.showRecommendedMovies(castMovies.getCast());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }
                });
    }

    @Override
    public void detachView() {
        view = null;
    }

    private Single<CastMovies> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(ApiTmdb.class)
                .getCastMovies(id, API_KEY_TMDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

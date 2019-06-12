package com.mythio.movii.activity.movie_details.dialog_contract;

import com.mythio.movii.model.cast.CastMovies;
import com.mythio.movii.network.EndPoint;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constants.API_KEY;

public class Presenter implements Contract.Presenter {

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

                    }
                });
    }

    @Override
    public void detachView() {
        view = null;
    }

    private Single<CastMovies> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClient()
                .create(EndPoint.class)
                .getCastMovies(id, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

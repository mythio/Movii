package com.mythio.movii.activity.tv_show_details.contract;

import androidx.annotation.NonNull;

public class Model implements Contract.Model {

    private static final String TAG = "movii.debug: Model";

    @Override
    public void getDetails(@NonNull final OnSeasonListener listener, Integer id) {

//        getTvShowTmdbObservable(id)
//                .flatMap((Function<TvShowTmdb, ObservableSource<TvShowOmdb>>)
//                        tvShowTmdb -> getTvShowOmdbObservable(tvShowTmdb.getExternalIds().getImdbId()), Utils::getTvShow)
//                .subscribe(new DisposableObserver<TvShow>() {
//                    @Override
//                    public void onNext(TvShow tvShow) {
//                        listener.onFinished(tvShow);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, "onError: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

//    private Observable<TvShowTmdb> getTvShowTmdbObservable(Integer id) {
//        return RetrofitBuilder
//                .getClientTmdb()
//                .create(EndPoint.class)
//                .getTvShowDetail(id, API_KEY_TMDB, "")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    private Observable<TvShowOmdb> getTvShowOmdbObservable(String id) {
//        return RetrofitBuilder
//                .getClientOmdb()
//                .create(EndPointsOmdb.class)
//                .getTvShowDetail(API_KEY_OMDB, id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
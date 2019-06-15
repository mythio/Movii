package com.mythio.movii.activity.movie_details.contract;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.network.ApiTmdb;
import com.mythio.movii.network.ApiVideoSpider;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constants.API_KEY_TMDB;
import static com.mythio.movii.util.Constants.API_KEY_VIDEO_SPIDER;
import static com.mythio.movii.util.Constants.API_SECRET_VIDEO_SPIDER;

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

                        onGetStreamLink("tt2316204");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onGetStreamLink(String imdbId) {
        getIpAddress()
                .flatMap((Function<String, SingleSource<String>>) ip -> {
                    Log.d(TAG, "onGetStreamLink: " + " IP ADDRESS: " + ip);
                    return getVideoSpiderTicket(imdbId, ip);
                })
                .flatMap((Function<String, SingleSource<String>>) ticket -> {
                    Log.d(TAG, "onGetStreamLink: " + " TICKET: " + ticket);
                    return getVideoSpiderStreamingLink(ticket, imdbId);
                })
                .subscribe(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "onSuccess: " + " STREAMING LINK: " + s);
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
                .getClientTmdb()
                .create(ApiTmdb.class)
                .getMovieDetail(id, API_KEY_TMDB, "recommendations,videos,credits")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<String> getIpAddress() {
        return RetrofitBuilder
                .getClientIpify()
                .create(ApiVideoSpider.class)
                .getIpAddress()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<String> getVideoSpiderTicket(String imdbId, String ip) {
        return RetrofitBuilder
                .getClientVideoSpiderTicket()
                .create(ApiVideoSpider.class)
                .getTicket(API_KEY_VIDEO_SPIDER, API_SECRET_VIDEO_SPIDER, imdbId, ip)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<String> getVideoSpiderStreamingLink(String imdbId, String ticket) {
        return RetrofitBuilder
                .getClientVideoSpiderStream()
                .create(ApiVideoSpider.class)
                .getLink(API_KEY_VIDEO_SPIDER, imdbId, ticket)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
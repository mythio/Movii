package com.mythio.movii.activity.moviedetails.contract;

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
    private Movie movie;
    private String ticket;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getDetails(int id) {
        getMovieTmdbObservable(id)
                .subscribe(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        Presenter.this.movie = movie;
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
    public void onGenerateStreamLink() {
        view.showStreamDialog();
        getIpAddress()
                .flatMap((Function<String, SingleSource<String>>) ip -> {
                    view.notifyDialogTicket();
                    return getVideoSpiderTicket(movie.getImdb(), ip);
                })
                .subscribe(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String ticket) {
                        Presenter.this.ticket = ticket;
                        view.notifyDialogSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }
                });
    }

    @Override
    public void onPlay() {
        view.streamInBrowser("https://videospider.stream/getvideo?key=u06QnFufrtjVbFBd&video_id=" + movie.getImdb() + "&ticket=" + ticket);
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
}
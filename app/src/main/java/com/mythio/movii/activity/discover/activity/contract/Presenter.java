package com.mythio.movii.activity.discover.activity.contract;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.tv_show.TvShow;
import com.mythio.movii.model.tv_show.TvShowResponse;
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
    public void getData() {
        getSinglePopularMovies()
                .map(MovieResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<Movie>>() {
                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        view.sendToMoviesFragment(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });

        getSinglePopularTvShows()
                .map(TvShowResponse::getResults)
                .subscribe(new DisposableSingleObserver<ArrayList<TvShow>>() {
                    @Override
                    public void onSuccess(ArrayList<TvShow> tvShows) {
                        view.sendToTvShowsFragment(tvShows);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void setFragment(BaseDiscoverFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void detachView() {
        view = null;
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

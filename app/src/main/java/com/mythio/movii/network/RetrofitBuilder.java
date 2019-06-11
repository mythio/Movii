package com.mythio.movii.network;

import androidx.annotation.NonNull;

import com.mythio.movii.util.App;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mythio.movii.util.Constant.BASE_URL_OMDB;
import static com.mythio.movii.util.Constant.BASE_URL_TMDB;

public class RetrofitBuilder {

    private static Retrofit retrofitOmdb = null;
    private static Retrofit retrofitTmdb = null;
    private static File httpCacheDirectory = new File(App.getContext().getCacheDir(), "httpCache");
    private static Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

    @NonNull
    public static Retrofit getClientTmdb() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TMDB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(getHttpClient())
                    .build();
        }
        return retrofitTmdb;
    }

    @NonNull
    public static Retrofit getClientOmdb() {
        if (retrofitOmdb == null) {
            retrofitOmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_OMDB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient())
                    .build();
        }
        return retrofitOmdb;
    }

    private static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                        .header("Cache-Control", "public, only-if-cached," + "max-stale=" + 60 * 60 * 24)
                        .build()))
                .build();
    }
}
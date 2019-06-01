package com.mythio.movii.network;

import androidx.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mythio.movii.util.Constant.BASE_URL_OMDB;
import static com.mythio.movii.util.Constant.BASE_URL_TMDB;

public class RetrofitBuilder {

    @Nullable
    private static Retrofit retrofitOmdb = null;
    @Nullable
    private static Retrofit retrofitTmdb = null;

    @Nullable
    public static Retrofit getClientTmdb() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TMDB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitTmdb;
    }

    @Nullable
    public static Retrofit getClientOmdb() {
        if (retrofitOmdb == null) {
            retrofitOmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_OMDB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitOmdb;
    }
}

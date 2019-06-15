package com.mythio.movii.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.mythio.movii.util.Constants.BASE_URL_IPIFY;
import static com.mythio.movii.util.Constants.BASE_URL_TMDB;
import static com.mythio.movii.util.Constants.BASE_URL_VIDEO_SPIDER_STREAM;
import static com.mythio.movii.util.Constants.BASE_URL_VIDEO_SPIDER_TICKET;

public class RetrofitBuilder {

    private static Retrofit retrofitTmdb = null;
    private static Retrofit retrofitIpify = null;
    private static Retrofit retrofitVideoSpiderTicket = null;
    private static Retrofit retrofitVideoSpiderStream = null;

    public static Retrofit getClientTmdb() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TMDB)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitTmdb;
    }

    public static Retrofit getClientIpify() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_IPIFY)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitIpify;
    }

    public static Retrofit getClientVideoSpiderTicket() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_VIDEO_SPIDER_TICKET)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitVideoSpiderTicket;
    }

    public static Retrofit getClientVideoSpiderStream() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL_VIDEO_SPIDER_STREAM)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitVideoSpiderStream;
    }
}
package com.mythio.movii.network;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mythio.movii.util.Constant.BASE_URL;

public class RetrofitBuilder {

    private static Retrofit retrofitTmdb = null;
//    private static File httpCacheDirectory = new File(App.getContext().getCacheDir(), "httpCache");
//    private static Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

    @NonNull
    public static Retrofit getClient() {
        if (retrofitTmdb == null) {
            retrofitTmdb = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitTmdb;
    }

//    private static OkHttpClient getHttpClient() {
//        return new OkHttpClient.Builder()
//                .cache(cache)
//                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
//                        .header("Cache-Control", "public, only-if-cached," + "max-stale=" + 60 * 60 * 24)
//                        .build()))
//                .build();
//    }
}
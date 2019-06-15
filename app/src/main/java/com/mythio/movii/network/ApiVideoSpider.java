package com.mythio.movii.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiVideoSpider {

    @GET("/")
    Single<String> getIpAddress(

    );

    @GET("getticket.php")
    Single<String> getTicket(
            @Query("key") String key,
            @Query("secret_key") String secret,
            @Query("video_id") String id,
            @Query("ip") String ip
    );

    @GET("getvideo")
    Single<String> getLink(
            @Query("key") String key,
            @Query("video_id") String id,
            @Query("ticket_id") String ticket_id
    );
}

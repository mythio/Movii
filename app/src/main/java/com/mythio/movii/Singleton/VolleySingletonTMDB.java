package com.mythio.movii.Singleton;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingletonTMDB {
    private static VolleySingletonTMDB mInstance;
    private RequestQueue mRequestQueue;

    private VolleySingletonTMDB(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingletonTMDB getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingletonTMDB(context);
        }

        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}

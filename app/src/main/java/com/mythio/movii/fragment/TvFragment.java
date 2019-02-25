package com.mythio.movii.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mythio.movii.R;
import com.mythio.movii.activity.ListActivity;
import com.mythio.movii.activity.SearchActivity;
import com.mythio.movii.adapter.SeriesSliderAdapter;
import com.mythio.movii.util.VolleySingleton;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Series;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static java.lang.Integer.max;

public class TvFragment extends Fragment {

    private RequestQueue mRequestQueue;
    private ViewPager viewPager;
    private ArrayList<Series> mSeries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tv, container, false);

        mRequestQueue = VolleySingleton.getInstance(getContext()).getmRequestQueue();

        mSeries = new ArrayList<>();

        viewPager = view.findViewById(R.id.view_pager_popular);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.text_view_title2).setAlpha(0.65F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.text_view_imdb_rating).setAlpha(1.0F - Math.abs(v) * 2);
            }
        });

        view.findViewById(R.id.text_view_popular_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                intent.putExtra("POPULAR_MOVIES_LIST", mSeries);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.search_go_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("SEARCH_ENDPOINT", "TV_SHOWS");
                startActivity(intent);
            }
        });

        autoScroll();

        parseTMDB();
        return view;
    }

    private void parseTMDB() {
        String url = Constants.TMDB_TV + "popular?api_key=" + TMDB_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                TvFragment.this.addToList(jsonArray.getJSONObject(i));
                                TvFragment.this.parseDataTMDB(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseDataTMDB(final int i) {
        String url = Constants.TMDB_TV + mSeries.get(i).getId() + "?api_key=" + Constants.TMDB_API_KEY + "&append_to_response=external_ids";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mSeries.get(i).setRuntime(/*response.getString("runtime")*/ "20");
                            response = response.getJSONObject("external_ids");
                            mSeries.get(i).setImdb_id(response.getString("imdb_id"));
                            TvFragment.this.parseDataOMDB(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    public void parseDataOMDB(final int i) {
        String url = Constants.OMDB_GET + "&i=" + mSeries.get(i).getImdb_id();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mSeries.get(i).setImdbRatings(response.getString("imdbRating"));
                            mSeries.get(i).setVote_count(response.getString("imdbVotes"));
                            mSeries.get(i).setOverview(response.getString("Plot"));
                            mSeries.get(i).setYear(response.getString("Year"));
                            mSeries.get(i).setImdbRatings(response.getString("imdbRating"));

                            if (mSeries.get(i).getImdbRatings().equals("N/A")) {
                                mSeries.get(i).setImdbRatings(mSeries.get(i).getVote_average());
                            }

                            if (i == mSeries.size() - 1) {
                                for (Series series : mSeries) {
                                    Log.d("TEMP_TEMP", series.getImdbRatings() + "");
                                }
                                viewPager.setAdapter(new SeriesSliderAdapter(TvFragment.this.getContext(), mSeries));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private void addToList(@NotNull JSONObject jsonObject) throws JSONException {
        Series series = new Series();
        series.setVote_count(String.valueOf(jsonObject.getInt("vote_count")));
        series.setVote_average(String.valueOf(jsonObject.getDouble("vote_average")));
        series.setId(String.valueOf(jsonObject.getInt("id")));
        series.setPoster_path(jsonObject.getString("poster_path"));
        series.setBackdrop(jsonObject.getString("backdrop_path"));
        series.setOverview(jsonObject.getString("overview"));
        series.setName(jsonObject.getString("name"));
        mSeries.add(series);
    }

    private void autoScroll() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (viewPager.getCurrentItem() == mSeries.size() - 1) {
                    viewPager.setCurrentItem(0, true);
                } else {
                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % max(1, mSeries.size()), true);
                }
            }
        };

        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }
}

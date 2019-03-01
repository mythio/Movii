package com.mythio.movii.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mythio.movii.R;
import com.mythio.movii.adapter.TvShowSearchAdapter;
import com.mythio.movii.model.TvShow;
import com.mythio.movii.util.VolleySingleton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static com.mythio.movii.constant.Constants.TMDB_SEARCH;
import static java.lang.Math.min;

public class SearchTvShowActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    private RecyclerView mRecyclerView;
    private EditText mSearchBar;

    private List<TvShow> mTvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv);

        mRequestQueue = VolleySingleton.getInstance(this).getmRequestQueue();

        mTvShows = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view);
        mSearchBar = findViewById(R.id.search_bar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new TvShowSearchAdapter.ItemDecorator(16));
        mRecyclerView.setHasFixedSize(true);

        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = String.valueOf(s);
                searchFor(query);
                findViewById(R.id.search_plate).setVisibility(View.GONE);
            }
        });
    }

    public void searchFor(String query) {
        String URL = TMDB_SEARCH + "tv?api_key=" + TMDB_API_KEY + "&query=" + Uri.encode(query);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            mTvShows.clear();
                            int p = min(jsonArray.length(), 7);
                            for (int i = 0; i < p; ++i) {
                                addToList(jsonArray.getJSONObject(i));
                            }

                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(SearchTvShowActivity.this, R.anim.layout_anim_fall);
                            mRecyclerView.setLayoutAnimation(controller);
                            mRecyclerView.scheduleLayoutAnimation();
                            mRecyclerView.setAdapter(new TvShowSearchAdapter(SearchTvShowActivity.this, mTvShows));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(request);
    }

    private void addToList(@NotNull JSONObject jsonObject) throws JSONException {
        TvShow tvShow = new TvShow();
        tvShow.setVote_count(String.valueOf(jsonObject.getInt("vote_count")));
        tvShow.setVote_average(String.valueOf(jsonObject.getDouble("vote_average")));
        tvShow.setId(String.valueOf(jsonObject.getInt("id")));
        tvShow.setPoster_path(jsonObject.getString("poster_path"));
        tvShow.setBackdrop(jsonObject.getString("backdrop_path"));
        tvShow.setOverview(jsonObject.getString("overview"));
        tvShow.setName(jsonObject.getString("name"));
        mTvShows.add(tvShow);
    }
}
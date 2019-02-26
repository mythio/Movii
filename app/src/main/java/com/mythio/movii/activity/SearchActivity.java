package com.mythio.movii.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieSearchAdapter;
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.fragment.MoviesFragment;
import com.mythio.movii.model.TvShow;
import com.mythio.movii.util.VolleySingleton;
import com.mythio.movii.model.Movie;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static com.mythio.movii.constant.Constants.TMDB_SEARCH;
import static java.lang.Math.min;

public class SearchActivity extends AppCompatActivity {

    private String search_endpoint;
    private RequestQueue mRequestQueue;

    private RecyclerView mRecyclerView;
    private EditText mSearchBar;

    private List<Movie> mMovies;
    int size = 0;
    private List<TvShow> mTvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_endpoint = Objects.requireNonNull(getIntent().getExtras()).getString("SEARCH_ENDPOINT");
        mRequestQueue = VolleySingleton.getInstance(this).getmRequestQueue();

        mMovies = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view);
        mSearchBar = findViewById(R.id.search_bar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = String.valueOf(s);
                searchForMovies(query);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    int si = 0;

    public void searchForMovies(String query) {
        String URL = TMDB_SEARCH + "movie?api_key=" + TMDB_API_KEY + "&query=" + Uri.encode(query);

        mMovies.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            int p = min(jsonArray.length(), 7);
                            si = p;
                            size = 0;
                            for (int i = 0; i < p; ++i) {
                                addToMoviesList(jsonArray.getJSONObject(i));
                            }

//                            AsyncTask.execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                }
//                            });

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

//
// test
//


    private void parseDataTMDB(final int i) {
        String url = Constants.TMDB_MOVIES + mMovies.get(i).getId() + "?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovies.get(i).setImdb_id(response.getString("imdb_id"));
                            mMovies.get(i).setRuntime(response.getString("runtime"));
                            parseDataOMDB(i);
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
        String url = Constants.OMDB_GET + "&i=" + mMovies.get(i).getImdb_id();
        Log.v("TAG_TAG_TAG*********", url);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovies.get(i).setImdbRatings(response.getString("imdbRating"));
                            mMovies.get(i).setVote_count(response.getString("imdbVotes"));
                            mMovies.get(i).setOverview(response.getString("Plot"));
                            mMovies.get(i).setYear(response.getString("Year"));
                            mMovies.get(i).setImdbRatings(response.getString("imdbRating"));

                            if (mMovies.get(i).getImdbRatings().equals("N/A")) {
                                mMovies.get(i).setImdbRatings(mMovies.get(i).getVote_average());
                            }

                            if (i == si - 1) {
                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(SearchActivity.this, R.anim.layout_anim_fall);
                                mRecyclerView.setLayoutAnimation(controller);
                                mRecyclerView.scheduleLayoutAnimation();
                                mRecyclerView.setAdapter(new MovieSearchAdapter(SearchActivity.this, mMovies));
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

    public void searchForShows(String query) {
        String URL = TMDB_SEARCH + "movie?api_key=" + TMDB_API_KEY + "&query=" + Uri.encode(query);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
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

    private void addToMoviesList(@NotNull JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();

        movie.setVote_count(String.valueOf(jsonObject.getInt("vote_count")));
        movie.setVote_average(String.valueOf(jsonObject.getDouble("vote_average")));
        movie.setId(String.valueOf(jsonObject.getInt("id")));
        movie.setPoster_path(jsonObject.getString("poster_path"));
        movie.setBackdrop(jsonObject.getString("backdrop_path"));
        movie.setOverview(jsonObject.getString("overview"));
        String title = jsonObject.getString("title");

        String[] title_arr = title.split(": ");
        String title1;
        String title2 = "";

        if (title_arr.length == 2) {
            title1 = title_arr[0].trim();
            title2 = title_arr[1].trim();
        } else {
            title1 = title_arr[0].trim();
        }

        movie.setTitle1(title1);
        movie.setTitle2(title2);
        mMovies.add(movie);
//        parseDataTMDB(size);
//        size++;
    }

    private void addToShowsList(@NotNull JSONObject jsonObject) throws JSONException {
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
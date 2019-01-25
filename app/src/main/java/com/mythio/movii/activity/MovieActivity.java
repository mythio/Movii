package com.mythio.movii.activity;

import android.animation.LayoutTransition;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mythio.movii.R;
import com.mythio.movii.Singleton.VolleySingletonTMDB;
import com.mythio.movii.adapter.SimilarMovieAdapter;
import com.mythio.movii.model.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.mythio.movii.constant.constants.OMDB_GET;
import static com.mythio.movii.constant.constants.OMDB_GET_END;
import static com.mythio.movii.constant.constants.TMDB_API_KEY;
import static com.mythio.movii.constant.constants.TMDB_IMAGE;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;
    private Movie movie;
    private ImageView mImageViewPoster;
    private TextView mTextViewRelease;
    private TextView mTextViewGenre;
    private TextView mTextViewTitle1;
    private TextView mTextViewTitle2;
    private TextView mTextViewLength;
    private TextView mTextViewOverview;
    private RatingBar mRatingBar;
    private TextView mTextViewVoteCount;
    private TextView mTextViewCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movie = (Movie) getIntent().getSerializableExtra("EXTRA");

        mRequestQueue = VolleySingletonTMDB.getInstance(this).getmRequestQueue();

        parseDataTMDB();
        parseDataOMDB();
        parseRecyclerView();

        initView();

        mMovies = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void parseDataTMDB() {
        String url = "https://api.themoviedb.org/3/movie/" + movie.getTmdb_id() + "/videos?api_key=" + TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String video_key = jsonObject.getString("key");
                            movie.setVideo_key(video_key);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    public void parseDataOMDB() {
        String title;
        if (movie.getTitle2().equals("")) {
            title = movie.getTitle1();
        } else {
            title = movie.getTitle1() + ": " + movie.getTitle2();
        }

        String url = OMDB_GET + Uri.encode(title) + OMDB_GET_END;
        Log.d("TAG__", url);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("TAG___", response.toString());
                            String length = response.getString("Runtime");
                            movie.setCast(response.getString("Actors"));
                            movie.setImdb(response.getString("imdbRating"));
                            movie.setVoteCount(response.getString("imdbVotes"));

                            String[] time = length.split(" ");
                            Integer time1 = Integer.valueOf(time[0]);

                            int HH = time1 / 60;
                            int MM = time1 % 60;

                            movie.setLength(HH + " h " + MM + " m");

                            mTextViewLength.setText(movie.getLength());
                            mTextViewCast.setText(movie.getCast());
                            mTextViewVoteCount.setText(movie.getVoteCount());

                            updateView();
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

    private void parseRecyclerView() {
        String url = "https://api.themoviedb.org/3/movie/" + movie.getTmdb_id() + "/similar?api_key=a781dd694991f0ea8dcf9050ec3e7a20";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String poster_path = jsonObject.getString("poster_path");

                                String[] title_arr = title.split(": ");
                                String title1;
                                String title2 = null;
                                if (title_arr.length == 2) {
                                    title1 = title_arr[0].trim();
                                    title2 = title_arr[1].trim();
                                } else {
                                    title1 = title_arr[0];
                                }

                                mMovies.add(new Movie(
                                        poster_path,
                                        title1,
                                        title2
                                ));
                            }
                            SimilarMovieAdapter adapter = new SimilarMovieAdapter(getApplicationContext(), mMovies);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private void initView() {

        mImageViewPoster = findViewById(R.id.image_view_poster);
        mTextViewRelease = findViewById(R.id.text_view_release);
        mTextViewGenre = findViewById(R.id.text_view_genre);
        mTextViewTitle1 = findViewById(R.id.text_view_title_1);
        mTextViewTitle2 = findViewById(R.id.text_view_title_2);
        mTextViewLength = findViewById(R.id.text_view_length);
        mTextViewOverview = findViewById(R.id.text_view_overview);
        mRatingBar = findViewById(R.id.rating_bar);
        mTextViewVoteCount = findViewById(R.id.text_view_vote_count);
        mTextViewCast = findViewById(R.id.text_view_cast);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void updateView() {
        String url = TMDB_IMAGE + "original" + movie.getPoster_path();
        Picasso.get().load(url).placeholder(R.color.colorAccent).resize(2000, 3000).centerInside().into(mImageViewPoster);
        mTextViewRelease.setText(movie.getRelease_date());
        mTextViewGenre.setText(movie.getGenre());

        if (movie.getTitle2().equals("")) {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.GONE);
        } else {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.VISIBLE);
            mTextViewTitle2.setText(movie.getTitle2());
        }

        mTextViewLength.setText(movie.getLength());
        mTextViewOverview.setText(movie.getOverview());

        double rating = Double.parseDouble(movie.getImdb());
        rating /= 2;
        rating = Math.round(rating * 2) / 2.0;

        mRatingBar.setRating((float) rating);
        mTextViewVoteCount.setText(movie.getVoteCount());
        mTextViewCast.setText(movie.getCast());

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(3000);

        fadeIn.reset();

        mRatingBar.setAnimation(fadeIn);
        mTextViewTitle1.startAnimation(fadeIn);
        mTextViewTitle2.startAnimation(fadeIn);
        mTextViewRelease.startAnimation(fadeIn);
        mTextViewGenre.startAnimation(fadeIn);
        mTextViewLength.startAnimation(fadeIn);
        mTextViewOverview.startAnimation(fadeIn);
        mRatingBar.startAnimation(fadeIn);
        mTextViewVoteCount.startAnimation(fadeIn);
        mTextViewCast.startAnimation(fadeIn);
    }
}

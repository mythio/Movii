package com.mythio.movii.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeIntents;
import com.mythio.movii.adapter.SimilarMovieAdapter;
import com.mythio.movii.model.Movie;
import com.mythio.movii.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;

    private ImageView mImageViewPoster;
    private TextView mTextViewRelease;
    private TextView mTextViewGenre;
    private TextView mTextViewTitle1;
    private TextView mTextViewTitle2;
    private TextView mTextViewRunTime;
    private TextView mTextViewOverview;
    private RatingBar mRatingBar;
    private TextView mTextViewRateCount;
    private TextView mTextViewCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();

//        mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                YouTubeIntents.canResolvePlayVideoIntent(MovieActivity.this);
//                startActivity(new Intent(MovieActivity.this, YoutubePlayer.class));
//            }
//        });

        mRequestQueue = Volley.newRequestQueue(this);
        mMovies = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RatingBar ratingBar = findViewById(R.id.rating_bar);

        ratingBar.setRating((float) 4.5);

        String url = "https://image.tmdb.org/t/p/original";
        url += "/AkJQpZp9WoNdj7pLYSj1L0RcMMN.jpg";
        Picasso.get().load(url).placeholder(R.color.colorAccent).resize(2000, 3000).centerInside().into(mImageView);

        parse();
    }

    private void initView() {

        Intent intent = getIntent();

        mImageViewPoster = findViewById(R.id.image_view_poster);
        mTextViewRelease = findViewById(R.id.text_view_release);
        mTextViewGenre = findViewById(R.id.text_view_genre);
        mTextViewTitle1 = findViewById(R.id.text_view_title_1);
        mTextViewTitle2 = findViewById(R.id.text_view_title_2);
        mTextViewRunTime = findViewById(R.id.text_view_runtime);
        mTextViewOverview = findViewById(R.id.text_view_overview);
        mRatingBar = findViewById(R.id.rating_bar);
        mTextViewRateCount = findViewById(R.id.text_view_rate_count);
        mTextViewCast = findViewById(R.id.text_view_cast);
        recyclerView = findViewById(R.id.recycler_view);


    }

    private void parse() {
        String url = "https://api.themoviedb.org/3/movie/353081/similar?api_key=a781dd694991f0ea8dcf9050ec3e7a20";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            Log.d("LOG_LOG_", response.toString());
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
}

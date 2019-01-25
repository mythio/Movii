package com.mythio.movii.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        final ImageView mImageView = findViewById(R.id.image_view_poster_bg);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouTubeIntents.canResolvePlayVideoIntent(MovieActivity.this);
                startActivity(new Intent(MovieActivity.this, YoutubePlayer.class));
            }
        });

        mRequestQueue = Volley.newRequestQueue(this);
        mMovies = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_similar_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RatingBar ratingBar = findViewById(R.id.rating_bar_stars);

        ratingBar.setRating((float) 4.5);

        String url = "https://image.tmdb.org/t/p/original";
        url += "/AkJQpZp9WoNdj7pLYSj1L0RcMMN.jpg";
        Picasso.get().load(url).placeholder(R.color.colorAccent).resize(2000, 3000).centerInside().into(mImageView);

        parse();
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

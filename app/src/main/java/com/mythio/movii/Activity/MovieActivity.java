package com.mythio.movii.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mythio.movii.Adapter.SimilarMovieAdapter;
import com.mythio.movii.Model.Movie;
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
        setContentView(R.layout.activity_movies);

        ImageView mImageView = findViewById(R.id.image_view_poster_bg);

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
                                String original_title = jsonObject.getString("title");
                                String poster_path = jsonObject.getString("poster_path");

                                mMovies.add(new Movie(
                                        poster_path,
                                        original_title
                                ));
                            }

                            Log.d("LOG_LOG", "### " + mMovies.size());

                            for (Movie m : mMovies) {
                                Log.d("LOG_LOG__", m.getTitle() + "");
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

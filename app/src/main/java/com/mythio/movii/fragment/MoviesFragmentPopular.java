package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieAdapter;
import com.mythio.movii.constant.constants;
import com.mythio.movii.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.mythio.movii.constant.constants.GENRE;
import static com.mythio.movii.constant.constants.TMDB_API_KEY;

public class MoviesFragmentPopular extends Fragment {

    private ArrayList<Movie> mMovies;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_popular, container, false);
        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        mMovies = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        parse();
        return view;
    }

    private void parse() {
        String url = constants.TMDB_MOVIES + "popular?api_key=" + TMDB_API_KEY;
        Log.d("tag_tag", url);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("LOG_LOG_", response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); ++i) {
                                addToList(jsonArray.getJSONObject(i));
                            }

                            MovieAdapter adapter = new MovieAdapter(getContext(), mMovies);
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

    private void addToList(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String poster_path = jsonObject.getString("poster_path");
        String id = String.valueOf(jsonObject.getInt("id"));
        JSONArray genreArr = jsonObject.getJSONArray("genre_ids");
        String release_date = jsonObject.getString("release_date");

        StringBuilder genre = null;
        String[] title_arr = title.split(": ");
        String title1;
        String title2 = "";

        if (title_arr.length == 2) {
            title1 = title_arr[0].trim();
            title2 = title_arr[1].trim();
        } else {
            title1 = title_arr[0].trim();
        }

        int l = Math.min(genreArr.length(), 2);

        for (int j = 0; j < l; ++j) {
            genre = (genre == null ? new StringBuilder() : genre).append(GENRE.get(genreArr.getInt(j)));
            if (j != l - 1) {
                genre.append("  |  ");
            }
        }

        mMovies.add(new Movie(
                poster_path,
                title1,
                title2,
                id,
                genre == null ? null : genre.toString(),
                release_date
        ));
    }
}
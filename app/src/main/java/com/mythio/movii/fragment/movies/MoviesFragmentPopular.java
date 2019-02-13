package com.mythio.movii.fragment.movies;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieAdapter;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;

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
        parseTMDB();
        return view;
    }

    private void parseTMDB() {
        String url = Constants.TMDB_MOVIES + "popular?api_key=" + TMDB_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            addToList(jsonArray.getJSONObject(i));
                            parseDataTMDB(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseDataTMDB(int i) {
        String url = Constants.TMDB_MOVIES + mMovies.get(i).getId() + "/external_ids?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        mMovies.get(i).setImdb_id(response.getString("imdb_id"));
                        parseDataOMDB(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    public void parseDataOMDB(int i) {
        String url = Constants.OMDB_GET + "&i=" + mMovies.get(i).getImdb_id();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String length = response.getString("Runtime");
                        mMovies.get(i).setImdbRatings(response.getString("imdbRating"));
                        mMovies.get(i).setVote_count(response.getString("imdbVotes"));
                        mMovies.get(i).setOverview(response.getString("Plot"));
                        mMovies.get(i).setYear(response.getString("Year"));

                        mMovies.get(i).setImdbRatings(response.getString("imdbRating"));
                        if (length.equals("N/A")) {
                            mMovies.get(i).setRuntime(length);
                        } else {
                            String[] time = length.split(" ");
                            Integer time1 = Integer.valueOf(time[0]);
                            int HH = time1 / 60;
                            int MM = time1 % 60;
                            mMovies.get(i).setRuntime(HH + " h " + MM + " m");
                        }
                        Log.d("TAG_TAG", i + " " + mMovies.get(i).getImdbRatings());

                        if (i == mMovies.size() - 1) {
                            MovieAdapter adapter = new MovieAdapter(getContext(), mMovies);
                            recyclerView.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void addToList(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String poster_path = jsonObject.getString("poster_path");
        String id = String.valueOf(jsonObject.getInt("id"));

        String[] title_arr = title.split(": ");
        String title1;
        String title2 = "";

        if (title_arr.length == 2) {
            title1 = title_arr[0].trim();
            title2 = title_arr[1].trim();
        } else {
            title1 = title_arr[0].trim();
        }

        mMovies.add(new Movie(
                poster_path,
                title1,
                title2,
                id
        ));
    }
}
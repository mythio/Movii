package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest
        ;
import com.mythio.movii.R;
import com.mythio.movii.adapter.SliderAdapter;
import com.mythio.movii.adapter.VolleySingleton;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;

public class MoviesFragment extends Fragment {

    private RequestQueue mRequestQueue;
    private ViewPager viewPager;
    private List<Movie> mMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mMovies = new ArrayList<>();
        mRequestQueue = VolleySingleton.getInstance(getContext()).getmRequestQueue();
        viewPager = view.findViewById(R.id.view_pager_popular);
        TabLayout indicator = view.findViewById(R.id.indicator);
//        viewPager.setAdapter(new SliderAdapter(getContext(), movies));
        indicator.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 4000);

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
        String url = Constants.TMDB_MOVIES + mMovies.get(i).getId() + "?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        mMovies.get(i).setImdb_id(response.getString("imdb_id"));
                        mMovies.get(i).setRuntime(response.getString("runtime"));
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
                        mMovies.get(i).setImdbRatings(response.getString("imdbRating"));
                        mMovies.get(i).setVote_count(response.getString("imdbVotes"));
                        mMovies.get(i).setOverview(response.getString("Plot"));
                        mMovies.get(i).setYear(response.getString("Year"));
                        mMovies.get(i).setImdbRatings(response.getString("imdbRating"));

                        if (mMovies.get(i).getImdbRatings().equals("N/A")) {
                            mMovies.get(i).setImdbRatings(mMovies.get(i).getVote_average());
                        } else {
                            double rating = Double.parseDouble(mMovies.get(i).getImdbRatings());
                            mMovies.get(i).setImdbRatings(String.valueOf(Math.round(rating * 2) / 2.0));
                        }

                        if (i == mMovies.size() - 1) {
                            viewPager.setAdapter(new SliderAdapter(getContext(), mMovies));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void addToList(JSONObject jsonObject) throws JSONException {
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
    }

    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                if (viewPager.getCurrentItem() < mMovies.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            });
        }
    }
}
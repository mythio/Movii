package com.mythio.movii.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.model.Movie;
import com.mythio.movii.util.VolleySingleton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mythio.movii.constant.Constants.OMDB_GET;
import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static com.mythio.movii.constant.Constants.TMDB_MOVIES;
import static java.lang.Math.max;

public class MoviesFragment extends Fragment {

    private RequestQueue mRequestQueue;
    private ViewPager viewPager;
    private ArrayList<Movie> mMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mMovies = new ArrayList<>();
        mRequestQueue = VolleySingleton.getInstance(getContext()).getmRequestQueue();
        viewPager = view.findViewById(R.id.view_pager_popular);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.text_view_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.play_btn).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.text_view_imdb_rating).setAlpha(1.0F - Math.abs(v) * 2);
            }
        });

        view.findViewById(R.id.text_view_popular_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                intent.putExtra("POPULAR_MOVIES_LIST", mMovies);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.search_go_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("SEARCH_ENDPOINT", "movie");
                startActivity(intent);
            }
        });

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (viewPager.getCurrentItem() == mMovies.size() - 1) {
                    viewPager.setCurrentItem(0, true);
                } else {
                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % max(mMovies.size(), 1), true);
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

        parseTMDB();

        return view;
    }

    private void parseTMDB() {
        String url = TMDB_MOVIES + "popular?api_key=" + TMDB_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                MoviesFragment.this.addToList(jsonArray.getJSONObject(i));
                                MoviesFragment.this.parseDataTMDB(i);
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
        String url = TMDB_MOVIES + mMovies.get(i).getId() + "?api_key=" + TMDB_API_KEY + "&append_to_response=videos";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovies.get(i).setImdb_id(response.getString("imdb_id"));
                            mMovies.get(i).setRuntime(response.getString("runtime"));

                            JSONArray videosResult = response.getJSONObject("videos").getJSONArray("results");

                            for (int k = 0; k < videosResult.length(); ++k) {
                                if (videosResult.getJSONObject(k).getString("key").equals("Trailer")) {
                                    mMovies.get(i).setKey(videosResult.getJSONObject(k).getString("key"));
                                    break;
                                }
                            }

                            MoviesFragment.this.parseDataOMDB(i);
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
        String url = OMDB_GET + "&i=" + mMovies.get(i).getImdb_id();

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

                            if (i == mMovies.size() - 1) {
                                viewPager.setAdapter(new MovieSliderAdapter(MoviesFragment.this.getContext(), mMovies));
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
}
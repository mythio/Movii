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
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.adapter.VolleySingleton;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;

public class MoviesFragment extends Fragment {

    private RequestQueue mRequestQueue;
    private ViewPager viewPager;
    private ArrayList<Movie> mMovies;
    private int currentPage = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mMovies = new ArrayList<>();
        mRequestQueue = VolleySingleton.getInstance(getContext()).getmRequestQueue();
        viewPager = view.findViewById(R.id.view_pager_popular);
        viewPager.setPageMargin(0);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {

                view.findViewById(R.id.imageView_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.textView_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.textView_title2).setAlpha(0.65F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.textView_imdb_rating).setAlpha(1.0F - Math.abs(v) * 2);
            }
        });

        view.findViewById(R.id.textView_popular_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                intent.putExtra("POPULAR_MOVIES_LIST", mMovies);
                startActivity(intent);
            }
        });

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 19) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
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
        String url = Constants.TMDB_MOVIES + "popular?api_key=" + TMDB_API_KEY;
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
        String url = Constants.TMDB_MOVIES + mMovies.get(i).getId() + "?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovies.get(i).setImdb_id(response.getString("imdb_id"));
                            mMovies.get(i).setRuntime(response.getString("runtime"));
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
        String url = Constants.OMDB_GET + "&i=" + mMovies.get(i).getImdb_id();

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
                            } else {
                                double rating = Double.parseDouble(mMovies.get(i).getImdbRatings());
                                mMovies.get(i).setImdbRatings(String.valueOf(Math.round(rating * 2) / 2.0));
                            }

                            if (i == mMovies.size() - 1) {
                                Collections.sort(mMovies, new Comparator<Movie>() {
                                    @Override
                                    public int compare(Movie o1, Movie o2) {
                                        return o2.getImdbRatings().compareTo(o1.getImdbRatings());
                                    }
                                });
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
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < mMovies.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
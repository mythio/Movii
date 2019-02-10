package com.mythio.movii.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.youtube.player.YouTubeIntents;
import com.mythio.movii.R;
import com.mythio.movii.adapter.CastAdapter;
import com.mythio.movii.adapter.SimilarMovieAdapter;
import com.mythio.movii.adapter.VolleySingleton;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.fragment.CastBottomSheetDialog;
import com.mythio.movii.model.Movie;
import com.mythio.movii.model.Person;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;
    private ArrayList<Person> mCasts;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCast;
    private Animation fadeIn;
    private Movie movie;

    private ImageView mImageViewPoster;
    private ImageView mImageViewPlay;
    private TextView mTextViewRelease;
    private TextView mTextViewGenre;
    private TextView mTextViewTitle1;
    private TextView mTextViewTitle2;
    private TextView mTextViewLength;
    private TextView mTextViewOverview;
    private RatingBar mRatingBar;
    private TextView mTextViewVoteCount;
    private TextView mTextViewCast;
    private TextView mTextViewMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();

        movie = (Movie) getIntent().getSerializableExtra("EXTRA");

        mRequestQueue = VolleySingleton.getInstance(this).getmRequestQueue();

        parseDataTMDB();
        parseCast();
        parseRecyclerView();
        parseDataTMDBVideo();

        mImageViewPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MovieActivity.this, YoutubePlayerActivity.class);
            intent.putExtra("EXTRAAA", movie.getVideo_key());
            YouTubeIntents.canResolvePlayVideoIntent(MovieActivity.this);
            startActivity(intent);
        });

        mTextViewMore.setOnClickListener(v -> {

        });

        mCasts = new ArrayList<>();
        mMovies = new ArrayList<>();

        recyclerViewCast.setHasFixedSize(true);
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void parseDataTMDB() {
        String url = Constants.TMDB_MOVIES + movie.getTmdb_id() + "/external_ids?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        movie.setImdb_id(response.getString("imdb_id"));
                        parseDataOMDB();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseDataTMDBVideo() {
        String url = Constants.TMDB_MOVIES + movie.getTmdb_id() + "/videos?api_key=" + Constants.TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String video_key = jsonObject.getString("key");
                        movie.setVideo_key(video_key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    public void parseDataOMDB() {
        String url = Constants.OMDB_GET + "&i=" + movie.getImdb_id();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String length = response.getString("Runtime");
                        String cast = response.getString("Actors");
                        movie.setImdb(response.getString("imdbRating"));
                        movie.setVoteCount(response.getString("imdbVotes"));
                        movie.setOverview(response.getString("Plot"));
                        movie.setRelease_date(response.getString("Year"));

                        movie.setCast(cast.replace(", ", "  |  "));

                        if (length.equals("N/A")) {
                            movie.setLength(length);
                        } else {
                            String[] time = length.split(" ");
                            Integer time1 = Integer.valueOf(time[0]);
                            int HH = time1 / 60;
                            int MM = time1 % 60;
                            movie.setLength(HH + " h " + MM + " m");
                        }

                        updateView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseCast() {
        String url = Constants.TMDB_MOVIES + movie.getTmdb_id() + "/credits?api_key=" + Constants.TMDB_API_KEY;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("cast");

                        for (int i = 0; i < jsonArray.length(); ++i) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            int id = jsonObject.getInt("id");
                            String profile_path = jsonObject.getString("profile_path");
                            if (!profile_path.equals("null")) {
                                mCasts.add(new Person(
                                        name,
                                        profile_path,
                                        id));
                            }
                        }

                        CastAdapter.ListItemClickListener listener = (view, position) -> {
                            CastBottomSheetDialog cast = new CastBottomSheetDialog();

                            Person person = mCasts.get(position);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("message", person);
                            cast.setArguments(bundle);
                            cast.show(getSupportFragmentManager(), "temp_tag");
                        };

                        CastAdapter adapter = new CastAdapter(mCasts, getApplicationContext(), listener);
                        recyclerViewCast.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseRecyclerView() {
        String url = Constants.TMDB_MOVIES + movie.getTmdb_id() + "/similar?api_key=" + Constants.TMDB_API_KEY;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
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
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void initView() {

        mImageViewPoster = findViewById(R.id.image_view_poster);
        mImageViewPlay = findViewById(R.id.image_view_play);
        mTextViewRelease = findViewById(R.id.text_view_release);
        mTextViewGenre = findViewById(R.id.text_view_genre);
        mTextViewTitle1 = findViewById(R.id.text_view_title_1);
        mTextViewTitle2 = findViewById(R.id.text_view_title_2);
        mTextViewLength = findViewById(R.id.text_view_length);
        mTextViewOverview = findViewById(R.id.text_view_overview);
        mRatingBar = findViewById(R.id.rating_bar);
        mTextViewVoteCount = findViewById(R.id.text_view_vote_count);
        mTextViewCast = findViewById(R.id.text_view_cast);
        mTextViewMore = findViewById(R.id.text_view_more);
        recyclerViewCast = findViewById(R.id.recycler_view_cast);
        mTextViewMore = findViewById(R.id.text_view_more);
        recyclerView = findViewById(R.id.recycler_view);
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1500);
    }

    private void updateView() {
        String url = Constants.TMDB_IMAGE + "original" + movie.getPoster_path();

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mImageViewPoster.setImageBitmap(bitmap);
                mImageViewPoster.setAlpha(0f);
                mImageViewPoster.animate().setDuration(1000).alpha(1f).start();

                Palette.from(bitmap)
                        .generate(palette -> {
                            Palette.Swatch textSwatch = Objects.requireNonNull(palette).getMutedSwatch();

                            mImageViewPlay.setVisibility(View.VISIBLE);
                            fadeIn.reset();

                            mImageViewPlay.setAnimation(fadeIn);
                            mImageViewPlay.setImageTintList(ColorStateList.valueOf(Objects.requireNonNull(textSwatch).getRgb()));
                        });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.movie_placeholder)
                .resize(2000, 3000)
                .centerInside()
                .into(target);

        mImageViewPoster.setTag(target);

        mTextViewRelease.setText(movie.getRelease_date());
        mTextViewGenre.setText(movie.getGenre());
        mTextViewLength.setText(movie.getLength());
        mTextViewOverview.setText(movie.getOverview());
        mTextViewVoteCount.setText(movie.getVoteCount());

        if (movie.getTitle2().equals("")) {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.GONE);
        } else {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.VISIBLE);
            mTextViewTitle2.setText(movie.getTitle2());
        }

        if (movie.getImdb().equals("N/A")) {
            mRatingBar.setRating(0);
        } else {
            double rating = Double.parseDouble(movie.getImdb());
            rating /= 2;
            movie.setImdb(String.valueOf(Math.round(rating * 2) / 2.0));
            mRatingBar.setRating(Float.parseFloat(movie.getImdb()));
        }

        mImageViewPoster.setVisibility(View.VISIBLE);
        mRatingBar.setVisibility(View.VISIBLE);
        mTextViewCast.setVisibility(View.VISIBLE);
        mTextViewMore.setVisibility(View.VISIBLE);

        mRatingBar.startAnimation(fadeIn);
        mImageViewPoster.startAnimation(fadeIn);
        mImageViewPlay.startAnimation(fadeIn);
        mTextViewTitle1.startAnimation(fadeIn);
        mTextViewTitle2.startAnimation(fadeIn);
        mTextViewRelease.startAnimation(fadeIn);
        mTextViewGenre.startAnimation(fadeIn);
        mTextViewLength.startAnimation(fadeIn);
        mTextViewOverview.startAnimation(fadeIn);
        mRatingBar.startAnimation(fadeIn);
        mTextViewVoteCount.startAnimation(fadeIn);
        mTextViewMore.startAnimation(fadeIn);
        mTextViewCast.startAnimation(fadeIn);
    }
}
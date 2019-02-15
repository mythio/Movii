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
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
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

import java.util.ArrayList;
import java.util.Objects;

public class MovieActivity extends AppCompatActivity {

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
    private TextView mTextViewSimilarMovies;

    public static final int SPACE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();

        movie = (Movie) getIntent().getSerializableExtra("MOVIE_ACTIVITY");

        mRequestQueue = VolleySingleton.getInstance(this).getmRequestQueue();

        parseDataTMDB();

        mImageViewPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MovieActivity.this, YoutubePlayerActivity.class);
            intent.putExtra("MOVIE_YOUTUBE_KEY", movie.getKey());
            YouTubeIntents.canResolvePlayVideoIntent(MovieActivity.this);
            startActivity(intent);
        });

        ImageButton imageButton = findViewById(R.id.close);
        imageButton.setOnClickListener(v -> finish());

        recyclerViewCast.setHasFixedSize(true);
        recyclerViewCast.addItemDecoration(new CastAdapter.ItemDecorator(SPACE));
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimilarMovieAdapter.ItemDecorator(SPACE));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void parseDataTMDB() {
        String url = Constants.TMDB_MOVIES + movie.getId() + "?api_key=" + Constants.TMDB_API_KEY + "&append_to_response=credits,similar,videos";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        ArrayList<Person> credits = new ArrayList<>();
                        ArrayList<Movie> similar = new ArrayList<>();
                        int L;

//                        Set cast
                        JSONArray creditsResult = response.getJSONObject("credits")
                                .getJSONArray("cast");
                        L = creditsResult.length();
                        for (int j = 0; j < L; ++j) {
                            Person person = new Person();
                            person.setId(creditsResult.getJSONObject(j).getInt("id"));
                            person.setName(creditsResult.getJSONObject(j).getString("name"));
                            person.setProfile_path(creditsResult.getJSONObject(j).getString("profile_path"));
                            credits.add(person);
                        }

                        Log.d("tag-tag-tag-tag", response.toString());
//                        Set similar movies
                        JSONArray similarResult = response.getJSONObject("similar")
                                .getJSONArray("results");
                        L = similarResult.length();
                        for (int j = 0; j < L; ++j) {
                            Log.d("tag-tag-tag-tag", similarResult.toString());
                            Movie movie = new Movie();
                            movie.setId(String.valueOf(similarResult.getJSONObject(j).getInt("id")));
                            movie.setOverview(similarResult.getJSONObject(j).getString("overview"));
                            movie.setPoster_path(similarResult.getJSONObject(j).getString("poster_path"));
                            movie.setVote_average(similarResult.getJSONObject(j).getString("vote_average"));
                            movie.setVote_count(similarResult.getJSONObject(j).getString("vote_count"));
                            String title = similarResult.getJSONObject(j).getString("title");

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
                            similar.add(movie);
                        }

//                        Set video key.
                        movie.setKey(response.getJSONObject("videos")
                                .getJSONArray("results")
                                .getJSONObject(0).getString("key"));

                        movie.setCast(credits);
                        movie.setSimilarMovies(similar);

                        updateView();
//                        setCastAdapter();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void setCastAdapter() {
        CastAdapter.ListItemClickListener listener = (view, position) -> {
            CastBottomSheetDialog cast = new CastBottomSheetDialog();
            Person person = movie.getCast().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("message", person);
            cast.setArguments(bundle);
            cast.show(getSupportFragmentManager(), "temp_tag");
        };
        CastAdapter adapter = new CastAdapter(movie.getCast(), getApplicationContext(), listener);
        recyclerViewCast.setAdapter(adapter);
    }

    private void setSimilarAdapter() {
        SimilarMovieAdapter adapter = new SimilarMovieAdapter(getApplicationContext(), movie.getSimilarMovies());
        recyclerView.setAdapter(adapter);
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
        recyclerViewCast = findViewById(R.id.recycler_view_cast);
        mTextViewSimilarMovies = findViewById(R.id.text_view_similar_movies);
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

        mTextViewRelease.setText(movie.getYear());
        mTextViewGenre.setText(movie.getGenre());
        mTextViewLength.setText(movie.getRuntime());
        mTextViewOverview.setText(movie.getOverview());
        mTextViewVoteCount.setText(movie.getVote_count());

        if (movie.getTitle2().equals("")) {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.GONE);
        } else {
            mTextViewTitle1.setText(movie.getTitle1());
            mTextViewTitle2.setVisibility(View.VISIBLE);
            mTextViewTitle2.setText(movie.getTitle2());
        }

        mRatingBar.setRating(Float.parseFloat(movie.getImdbRatings()));
        setCastAdapter();
        setSimilarAdapter();

        mImageViewPoster.setVisibility(View.VISIBLE);
        mRatingBar.setVisibility(View.VISIBLE);
        mTextViewCast.setVisibility(View.VISIBLE);
        mTextViewSimilarMovies.setVisibility(View.VISIBLE);
        recyclerViewCast.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

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
        mTextViewCast.startAnimation(fadeIn);
        mTextViewSimilarMovies.setAnimation(fadeIn);
        recyclerViewCast.startAnimation(fadeIn);
        recyclerView.startAnimation(fadeIn);
    }
}
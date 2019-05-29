package com.mythio.movii.activity.MovieDetails;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.MovieDetails.contract.Contract;
import com.mythio.movii.activity.MovieDetails.contract.Presenter;
import com.mythio.movii.adapter.recyclerViewAdapter.CastAdapter;
import com.mythio.movii.adapter.recyclerViewAdapter.RecommendedMoviesAdapter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;

    @BindView(R.id.img_view_bg_grad)
    ImageView imgViewBgGrad;

    @BindView(R.id.img_view_play)
    ImageButton imgViewPlay;

    @BindView(R.id.txt_view_year)
    TextView txtViewYear;

    @BindView(R.id.txt_view_genre)
    TextView txtViewGenre;

    @BindView(R.id.txt_view_title_1)
    TextView txtViewTitle1;

    @BindView(R.id.txt_view_title_2)
    TextView txtViewTitle2;

    @BindView(R.id.txt_view_runtime)
    TextView txtViewRuntime;

    @BindView(R.id.txt_view_overview)
    TextView txtViewOverview;

    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.txt_view_rating)
    TextView txtViewRating;

    @BindView(R.id.txt_view_vote_count)
    TextView txtViewVoteCount;

    @BindView(R.id.recycler_view_cast)
    RecyclerView recyclerViewCast;

    @BindView(R.id.recycler_view_recommended)
    RecyclerView recyclerViewRecommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");
        Contract.Presenter presenter = new Presenter(this);

        presenter.getDetails(currentId);
    }

    @Override
    public void showDetails(Movie movie) {

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imgViewPoster.setImageBitmap(bitmap);
                Log.d("TAG_TAG_TAG_PICASSO", "onBitmapLoaded()");
                Palette.from(bitmap).generate(palette -> {
                    if (palette.getDarkMutedSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getDarkMutedSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
                    } else if (palette.getDarkVibrantSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getDarkVibrantSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                    } else if (palette.getMutedSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getMutedSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getMutedSwatch().getRgb());
                    }
                });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("TAG_TAG_TAG_PICASSO", "onBitmapFailed(): " + e.getLocalizedMessage());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("TAG_TAG_TAG_PICASSO", "onPrepareLoad()");
            }
        };

        Picasso.get()
                .load(IMAGE_BASE_URL + "original" + movie.getPosterPath())
                .into(target);

        imgViewPoster.setTag(target);

        if (movie.getTitle2().equals("")) {
            txtViewTitle1.setText(movie.getTitle1());
            txtViewTitle2.setVisibility(View.GONE);
        } else {
            txtViewTitle1.setText(movie.getTitle1());
            txtViewTitle2.setVisibility(View.VISIBLE);
            txtViewTitle2.setText(movie.getTitle2());
        }

        txtViewYear.setText(movie.getYear());
        txtViewGenre.setText(movie.getGenres());
        txtViewRuntime.setText(movie.getRuntime());
        txtViewOverview.setText(movie.getOverview());
        ratingBar.setRating(Float.valueOf(movie.getRating()) / 2);
        txtViewRating.setText(movie.getRating());
        txtViewVoteCount.setText(movie.getVotes());

        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewCast.addItemDecoration(new ItemDecorator(16, 1));
        CastAdapter castAdapter = new CastAdapter(movie.getCasts(), this);
        recyclerViewCast.setAdapter(castAdapter);

        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewRecommended.addItemDecoration(new ItemDecorator(24, 1));
        RecommendedMoviesAdapter adapter = new RecommendedMoviesAdapter(this, movie.getRecommendations());
        recyclerViewRecommended.setAdapter(adapter);
    }
}
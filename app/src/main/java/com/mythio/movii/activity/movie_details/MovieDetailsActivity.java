package com.mythio.movii.activity.movie_details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mythio.movii.R;
import com.mythio.movii.activity.movie_details.contract.Contract;
import com.mythio.movii.activity.movie_details.contract.Presenter;
import com.mythio.movii.adapter.recycler_view_adapter.cast.CastAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.cast.CastPresenter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesPresenter;
import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.App.getContext;
import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.anim)
    LottieAnimationView animationView;

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

        int id = getIntent().getIntExtra("BUNDLED_EXTRA_MOVIE_ID", 0);
        Presenter presenter = new Presenter(this);

        presenter.getDetails(id);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showMovieDetails(@NonNull Movie movie) {
        Glide.with(getContext())
                .asBitmap()
                .load(IMAGE_BASE_URL + "w780" + movie.getPosterPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        imgViewPoster.setImageBitmap(resource);
                        Palette.from(resource).generate(palette -> {
                            assert palette != null;
                            if (palette.getDarkMutedSwatch() != null) {
                                int rgb = palette.getDominantSwatch().getRgb();

                                imgViewBgGrad.setImageTintList(ColorStateList.valueOf(rgb));
                                getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getDarkVibrantSwatch() != null) {
                                int rgb = palette.getDarkVibrantSwatch().getRgb();

                                imgViewBgGrad.setImageTintList(ColorStateList.valueOf(rgb));
                                getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getMutedSwatch() != null) {
                                int rgb = palette.getMutedSwatch().getRgb();

                                imgViewBgGrad.setImageTintList(ColorStateList.valueOf(rgb));
                                getWindow().getDecorView().setBackgroundColor(rgb);
                            }

                            if (palette.getLightMutedSwatch() != null) {
                                int rgb = palette.getLightMutedSwatch().getRgb();
                                imgViewPlay.setImageTintList(ColorStateList.valueOf(rgb));
                            } else if (palette.getLightVibrantSwatch() != null) {
                                int rgb = palette.getLightVibrantSwatch().getRgb();
                                imgViewPlay.setImageTintList(ColorStateList.valueOf(rgb));
                            }
                        });
                        return false;
                    }
                })
                .into(imgViewPoster);

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

        animationView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_out));
        animationView.setVisibility(View.GONE);
        animationView.cancelAnimation();
    }

    @Override
    public void showCastRecyclerView(ArrayList<Cast> casts) {
        recyclerViewCast.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewCast.addItemDecoration(new ItemDecorator(32, ItemDecorator.HORIZONTAL));

        CastPresenter castPresenter = new CastPresenter(casts);
        CastAdapter castAdapter = new CastAdapter(castPresenter, (position, imageView) -> {
            CastBottomDialog b = new CastBottomDialog();
            Bundle bundle = new Bundle();
            bundle.putString("123", casts.get(position).getProfilePath());
            bundle.putString("234", casts.get(position).getName());
            bundle.putString("345", casts.get(position).getCharacter());
            bundle.putString("456", casts.get(position).getCreditId());
            bundle.putInt("int", casts.get(position).getId());
            bundle.putInt("color", ImageViewCompat.getImageTintList(imgViewBgGrad).getDefaultColor());
            b.setArguments(bundle);
            b.show(getSupportFragmentManager(), b.getTag());
        });
        recyclerViewCast.setAdapter(castAdapter);
    }

    @Override
    public void showRecommendationsRecyclerView(ArrayList<MovieTmdb> movies) {
        recyclerViewRecommended.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewRecommended.addItemDecoration(new ItemDecorator(24, ItemDecorator.HORIZONTAL));

        RecommendedMoviesPresenter recommendedMoviesPresenter = new RecommendedMoviesPresenter(movies);


        RecommendedMoviesAdapter recommendedMoviesAdapter = new RecommendedMoviesAdapter(recommendedMoviesPresenter, position -> {
            Intent intent = new Intent(MovieDetailsActivity.this, MovieDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", movies.get(position).getId());
            startActivity(intent);
            finish();
        });
        recyclerViewRecommended.setAdapter(recommendedMoviesAdapter);
    }
}
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
import androidx.core.app.ActivityOptionsCompat;
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
import com.mythio.movii.activity.about_cast.AboutCastActivity;
import com.mythio.movii.activity.movie_details.contract.Contract;
import com.mythio.movii.activity.movie_details.contract.Presenter;
import com.mythio.movii.adapter.recycler_view_adapter.cast.CastAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.cast.CastPresenter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesPresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;

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

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");
        Contract.Presenter presenter = new Presenter(this);

        presenter.getDetails(currentId);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showDetails(@NonNull Movie movie) {


        Glide.with(getContext())
                .asBitmap()
                .load(IMAGE_BASE_URL + "w780" + movie.getPosterPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        imgViewPoster.setImageBitmap(resource);
                        Palette.from(resource).generate(palette -> {
                            assert palette != null;
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

                            if (palette.getLightMutedSwatch() != null) {
                                imgViewPlay.setImageTintList(ColorStateList.valueOf(palette.getLightMutedSwatch().getRgb()));
                            } else if (palette.getLightVibrantSwatch() != null) {
                                imgViewPlay.setImageTintList(ColorStateList.valueOf(palette.getLightVibrantSwatch().getRgb()));
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

        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewCast.addItemDecoration(new ItemDecorator(32, ItemDecorator.HORIZONTAL));

        CastPresenter castPresenter = new CastPresenter(movie.getCasts());
        CastAdapter castAdapter = new CastAdapter(castPresenter, (id, imageView) -> {
            Intent intent = new Intent(getContext(), AboutCastActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", movie.getCasts().get(id).getProfilePath());
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.getTransitionName());
            startActivity(intent, compat.toBundle());
        });
        recyclerViewCast.setAdapter(castAdapter);

        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewRecommended.addItemDecoration(new ItemDecorator(24, ItemDecorator.HORIZONTAL));

        RecommendedMoviesPresenter recommendedMoviesPresenter = new RecommendedMoviesPresenter(movie.getRecommendations());
        RecommendedMoviesAdapter recommendedMoviesAdapter = new RecommendedMoviesAdapter(recommendedMoviesPresenter, null);
        recyclerViewRecommended.setAdapter(recommendedMoviesAdapter);

        animationView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_out));
        animationView.setVisibility(View.GONE);
        animationView.cancelAnimation();
    }
}
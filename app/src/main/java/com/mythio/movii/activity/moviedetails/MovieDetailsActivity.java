package com.mythio.movii.activity.moviedetails;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.button.MaterialButton;
import com.mythio.movii.R;
import com.mythio.movii.activity.moviedetails.contract.Contract;
import com.mythio.movii.activity.moviedetails.contract.Presenter;
import com.mythio.movii.adapter.cast.CastAdapter;
import com.mythio.movii.adapter.cast.CastPresenter;
import com.mythio.movii.adapter.recommendationmovies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recommendationmovies.RecommendedMoviesPresenter;
import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mythio.movii.util.App.getContext;
import static com.mythio.movii.util.Constants.BASE_URL_IMAGE;

public class MovieDetailsActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.anim)
    LottieAnimationView animationView;

    @BindView(R.id.iv_poster)
    ImageView imgViewPoster;

    @BindView(R.id.v_bg_grad)
    View imgViewBgGrad;

    @BindView(R.id.tv_year)
    TextView txtViewYear;

    @BindView(R.id.tv_genre)
    TextView txtViewGenre;

    @BindView(R.id.tv_title_1)
    TextView txtViewTitle1;

    @BindView(R.id.tv_title_2)
    TextView txtViewTitle2;

    @BindView(R.id.tv_runtime)
    TextView txtViewRuntime;

    @BindView(R.id.tv_overview)
    TextView txtViewOverview;

    @BindView(R.id.btn_trailer)
    MaterialButton btnTrailer;

    @BindView(R.id.btn_stream)
    MaterialButton btnStream;

    @BindView(R.id.ratingbar)
    RatingBar ratingBar;

    @BindView(R.id.tv_rating)
    TextView txtViewRating;

    @BindView(R.id.tv_vote_count)
    TextView txtViewVoteCount;

    @BindView(R.id.rv_cast)
    RecyclerView recyclerViewCast;

    @BindView(R.id.rv_recommended)
    RecyclerView recyclerViewRecommended;

    private Contract.Presenter mPresenter;
    private AlertDialog streamDialog;
    private final AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
    private final AlphaAnimation fadeIn = new AlphaAnimation(0, 1);

    DialogViewHolder dialogViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setPresenter(new Presenter(this));
        ButterKnife.bind(this);

        fadeOut.setDuration(400);
        fadeIn.setDuration(400);

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View streamView = LayoutInflater
                .from(this)
                .inflate(R.layout.dialog_streaming, viewGroup, false);

        dialogViewHolder = new DialogViewHolder(streamView);

        streamDialog = new AlertDialog
                .Builder(this)
                .setView(streamView)
                .setCancelable(false)
                .create();

        streamDialog.setCanceledOnTouchOutside(false);

        streamDialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogViewHolder.dialogBtnCancel.setOnClickListener(view -> streamDialog.dismiss());

        int id = getIntent().getIntExtra("BUNDLED_EXTRA_MOVIE_ID", 0);
        mPresenter.getDetails(id);
    }

    @Override
    public void notifyDialogTicket() {
        dialogViewHolder.dialogTvTicket.setVisibility(View.VISIBLE);
        dialogViewHolder.dialogTvTicket.startAnimation(fadeIn);
        dialogViewHolder.dialogTvIp.setVisibility(View.INVISIBLE);
        dialogViewHolder.dialogTvIp.startAnimation(fadeOut);
    }

    @Override
    public void streamInBrowser(String imdbId, String ticket) {
        streamDialog.show();

        dialogViewHolder.dialogAnim.setVisibility(View.VISIBLE);
        dialogViewHolder.dialogAnim.setAnimation(fadeIn);
        dialogViewHolder.dialogTvIp.setVisibility(View.VISIBLE);
        dialogViewHolder.dialogTvIp.setAnimation(fadeIn);
    }

    @Override
    public void notifyDialogSuccess() {
        dialogViewHolder.dialogTvSuccess.setVisibility(View.VISIBLE);
        dialogViewHolder.dialogTvSuccess.startAnimation(fadeIn);
        dialogViewHolder.dialogTvTicket.setVisibility(View.INVISIBLE);
        dialogViewHolder.dialogTvTicket.startAnimation(fadeOut);
        dialogViewHolder.dialogAnim.setVisibility(View.INVISIBLE);
        dialogViewHolder.dialogAnim.setAnimation(fadeOut);
        dialogViewHolder.dialogIbPlay.setVisibility(View.VISIBLE);
        dialogViewHolder.dialogIbPlay.startAnimation(fadeIn);
    }

    static class DialogViewHolder {

        @BindView(R.id.anim)
        LottieAnimationView dialogAnim;

        @BindView(R.id.ib_play)
        ImageButton dialogIbPlay;

        @BindView(R.id.tv_dialog_ip)
        TextView dialogTvIp;

        @BindView(R.id.tv_dialog_ticket)
        TextView dialogTvTicket;

        @BindView(R.id.tv_dialog_success)
        TextView dialogTvSuccess;

        @BindView(R.id.btn_cancel)
        Button dialogBtnCancel;

        DialogViewHolder(View rootView) {
            ButterKnife.bind(this, rootView);
        }
    }

    @OnClick(R.id.btn_stream)
    public void btnStream() {
        mPresenter.onGenerateStreamLink();
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMovieDetails(@NonNull Movie movie) {
        RequestListener<Bitmap> listener = new RequestListener<Bitmap>() {
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
                        int rgb = ColorUtils.blendARGB(
                                palette.getDarkMutedSwatch().getRgb(),
                                Color.BLACK,
                                0.3f
                        );
                        imgViewBgGrad.setBackgroundTintList(ColorStateList.valueOf(rgb));
                        getWindow().getDecorView().setBackgroundColor(rgb);
                    } else if (palette.getMutedSwatch() != null) {
                        int rgb = ColorUtils.blendARGB(
                                palette.getMutedSwatch().getRgb(),
                                Color.BLACK,
                                0.3f
                        );
                        imgViewBgGrad.setBackgroundTintList(ColorStateList.valueOf(rgb));
                        getWindow().getDecorView().setBackgroundColor(rgb);
                    } else if (palette.getDarkVibrantSwatch() != null) {
                        int rgb = ColorUtils.blendARGB(
                                palette.getDarkVibrantSwatch().getRgb(),
                                Color.BLACK,
                                0.3f
                        );
                        imgViewBgGrad.setBackgroundTintList(ColorStateList.valueOf(rgb));
                        getWindow().getDecorView().setBackgroundColor(rgb);
                    }

                    if (palette.getLightVibrantSwatch() != null) {
                        int rgb = palette.getLightVibrantSwatch().getRgb();
                        btnStream.setRippleColor(ColorStateList.valueOf(rgb));
                        btnTrailer.setRippleColor(ColorStateList.valueOf(rgb));
                    }
                });
                return false;
            }
        };

        Glide.with(getContext())
                .asBitmap()
                .load(BASE_URL_IMAGE + "w780" + movie.getPosterPath())
                .listener(listener)
                .into(imgViewPoster);

        String[] title_arr = movie.getTitle().split(": ");
        if (title_arr.length == 2) {
            txtViewTitle1.setText(title_arr[0].trim());
            txtViewTitle2.setVisibility(View.VISIBLE);
            txtViewTitle2.setText(title_arr[1].trim());
        } else {
            txtViewTitle1.setText(title_arr[0].trim());
            txtViewTitle2.setVisibility(View.GONE);
        }

        StringBuilder genre = new StringBuilder();

        for (int i = 0; i < Math.min(3, movie.getGenres().size()); ++i) {
            genre.append(movie.getGenres().get(i).getName());
            if (i < Math.min(3, movie.getGenres().size()) - 1) {
                genre.append("  |  ");
            }
        }

        txtViewYear.setText(movie.getReleaseDate());
        txtViewGenre.setText(genre.toString());
        txtViewRuntime.setText(movie.getRuntime() / 60 + " hours " + movie.getRuntime() % 60 + " mins");
        txtViewOverview.setText(movie.getOverview());
        ratingBar.setRating(movie.getVoteAverage() / 2);
        txtViewRating.setText(String.valueOf(movie.getVoteAverage()));
        txtViewVoteCount.setText(NumberFormat.getNumberInstance(Locale.US).format(movie.getVoteCount()));

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
            bundle.putInt("color", ViewCompat.getBackgroundTintList(imgViewBgGrad).getDefaultColor());
            b.setArguments(bundle);
            b.show(getSupportFragmentManager(), b.getTag());
        });
        recyclerViewCast.setAdapter(castAdapter);
    }

    @Override
    public void showRecommendationsRecyclerView(ArrayList<Movie> movies) {
        recyclerViewRecommended.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG_TAG", "onDestroy");
        mPresenter.detachView();
    }
}
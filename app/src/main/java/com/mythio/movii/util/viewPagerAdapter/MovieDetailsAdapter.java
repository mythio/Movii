package com.mythio.movii.util.viewPagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;
import com.mythio.movii.util.recyclerViewAdapter.CastAdapter;
import com.mythio.movii.util.recyclerViewAdapter.RecommendedMoviesAdapter;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieDetailsAdapter extends PagerAdapter {

    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;

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
    TextView rating;

    @BindView(R.id.txt_view_vote_count)
    TextView txtViewVoteCount;

    @BindView(R.id.recycler_view_cast)
    RecyclerView recyclerViewCast;

    @BindView(R.id.recycler_view_recommended)
    RecyclerView recyclerViewRecommended;

    private final Context mContext;
    private final ArrayList<Movie> mMovies;

    public MovieDetailsAdapter(Context mContext, ArrayList<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Movie movie = mMovies.get(position);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_movie_details, null);
        ButterKnife.bind(this, view);

        String[] title = movie.getTitle().split(": ");

        txtViewYear.setText(movie.getYear());

        if (title.length == 2) {
            txtViewTitle1.setText(title[0].trim());
            txtViewTitle2.setText(title[1].trim());
        } else {
            txtViewTitle1.setText(title[0].trim());
            txtViewTitle2.setVisibility(View.GONE);
        }

        rating.setText(movie.getRating());

        txtViewGenre.setText(movie.getGenres());
        txtViewRuntime.setText(movie.getRuntime());
        txtViewOverview.setText(movie.getOverview());
        ratingBar.setRating(Float.valueOf(movie.getRating()) / 2);
        txtViewVoteCount.setText(movie.getVotes());
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCast.addItemDecoration(new ItemDecorator(12));
        recyclerViewCast.setAdapter(new CastAdapter(movie.getCasts(), view.getContext()));
        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecommended.addItemDecoration(new ItemDecorator(36));
        recyclerViewRecommended.setAdapter(new RecommendedMoviesAdapter(view.getContext(), movie.getMoviesTmdb()));

        Picasso.get().load(IMAGE_BASE_URL + "w780" + movie.getPosterPath()).into(imgViewPoster);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}

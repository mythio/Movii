package com.mythio.movii.adapter.viewPagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.model.season.SeasonDetails;
import com.mythio.movii.model.tvShow.TvShow;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class TvShowDetailsAdapter extends PagerAdapter {

    private final Context mContext;
    private final TvShow tvShow;

//    @BindView(R.id.txt_view_runtime)
//    TextView txtViewRuntime;
//
//    @BindView(R.id.txt_view_overview)
//    TextView txtViewOverview;
//
//    @BindView(R.id.rating_bar)
//    RatingBar ratingBar;
//
//    @BindView(R.id.txt_view_rating)
//    TextView rating;
//
//    @BindView(R.id.txt_view_vote_count)
//    TextView txtViewVoteCount;
//
//    @BindView(R.id.recycler_view_cast)
//    RecyclerView recyclerViewCast;
//
//    @BindView(R.id.recycler_view_recommended)
//    RecyclerView recyclerViewRecommended;

    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;
//
//    @BindView(R.id.img_view_play)
//    ImageButton imgViewPlay;
//
//    @BindView(R.id.txt_view_year)
//    TextView txtViewYear;
//
//    @BindView(R.id.txt_view_genre)
//    TextView txtViewGenre;

    @BindView(R.id.txt_view_title_1)
    TextView txtViewTitle1;
    @BindView(R.id.txt_view_title_2)
    TextView txtViewTitle2;

    public TvShowDetailsAdapter(Context mContext, TvShow tvShow) {
        this.mContext = mContext;
        this.tvShow = tvShow;
    }

    @Override
    public int getCount() {
        return tvShow.getSeasons().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final SeasonDetails season = tvShow.getSeasons().get(position);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_movie_details, null);
        ButterKnife.bind(this, view);

        txtViewTitle1.setText(tvShow.getName());
        txtViewTitle2.setText(season.getName());
        Picasso.get().load(IMAGE_BASE_URL + "w780" + season.getPosterPath()).into(imgViewPoster);

//        String[] title = movie.getTitle().split(": ");
//
//        txtViewYear.setText(movie.getYear());
//
//        if (title.length == 2) {
//            txtViewTitle1.setText(title[0].trim());
//            txtViewTitle2.setText(title[1].trim());
//        } else {
//            txtViewTitle1.setText(title[0].trim());
//            txtViewTitle2.setVisibility(View.GONE);
//        }
//
//        rating.setText(movie.getRating());
//
//        txtViewGenre.setText(movie.getGenres());
//        txtViewRuntime.setText(movie.getRuntime());
//        txtViewOverview.setText(movie.getOverview());
//        ratingBar.setRating(Float.valueOf(movie.getRating()) / 2);
//        txtViewVoteCount.setText(movie.getVotes());
//        recyclerViewCast.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewCast.addItemDecoration(new ItemDecorator(12, 1));
//        recyclerViewCast.setAdapter(new CastAdapter(movie.getCasts(), view.getContext()));
//        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewRecommended.addItemDecoration(new ItemDecorator(36, 0));
//        recyclerViewRecommended.setAdapter(new RecommendedMoviesAdapter(view.getContext(), movie.getMoviesTmdb()));
//        recyclerViewRecommended.setDrawingCacheEnabled(true);
//        recyclerViewRecommended.setItemViewCacheSize(6);
//        recyclerViewRecommended.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//        Picasso.get().load(IMAGE_BASE_URL + "w780" + movie.getPosterPath()).into(imgViewPoster);

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

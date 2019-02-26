package com.mythio.movii.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeIntents;
import com.mythio.movii.R;
import com.mythio.movii.activity.YouTubePlayerActivity;
import com.mythio.movii.model.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class MovieSliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<Movie> movies;

    public MovieSliderAdapter(Context mContext, List<Movie> movies) {
        this.mContext = mContext;
        this.movies = movies;
//        Collections.sort(movies, new Comparator<Movie>() {
//            @Override
//            public int compare(Movie o1, Movie o2) {
//                return o2.getImdbRatings().compareTo(o1.getImdbRatings());
//            }
//        });
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int i) {
        final Movie movie = movies.get(i);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        TextView textViewTitle1 = view.findViewById(R.id.text_view_title1);
        TextView textViewTitle2 = view.findViewById(R.id.text_view_title2);
        TextView textViewRating = view.findViewById(R.id.text_view_imdb_rating);

        view.findViewById(R.id.play_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, YouTubePlayerActivity.class);
                intent.putExtra("MOVIE_YOUTUBE_KEY", movie.getKey());
                YouTubeIntents.canResolvePlayVideoIntent(mContext);
                mContext.startActivity(intent);
            }
        });

        textViewRating.setText(movie.getImdbRatings());

        if (movie.getTitle2().equals("")) {
            textViewTitle1.setText(movie.getTitle1());
            textViewTitle2.setVisibility(View.GONE);
        } else {
            textViewTitle1.setText(movie.getTitle1());
            textViewTitle2.setVisibility(View.VISIBLE);
            textViewTitle2.setText(movie.getTitle2());
        }

        String url = TMDB_IMAGE + "w780" + movie.getBackdrop();

        Picasso.get()
                .load(url)
                .into(imageViewBackdrop);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
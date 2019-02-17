package com.mythio.movii.adapter;

import android.content.Context;
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

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class MovieSliderAdapter extends PagerAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieSliderAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
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
    public Object instantiateItem(ViewGroup container, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Movie movie = movies.get(i);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.imageView_backdrop);
        final ImageView imageViewOverlay = view.findViewById(R.id.imageView_overlay);
        TextView textViewTitle1 = view.findViewById(R.id.textView_title1);
        TextView textViewTitle2 = view.findViewById(R.id.textView_title2);
        TextView textViewRating = view.findViewById(R.id.textView_imdb_rating);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageViewBackdrop.setImageBitmap(bitmap);
                Palette.from(bitmap)
                        .generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                Palette.Swatch textSwatch = Objects.requireNonNull(palette).getDominantSwatch();
                                imageViewOverlay.setImageTintList(ColorStateList.valueOf(Objects.requireNonNull(textSwatch).getRgb()));
                            }
                        });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

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
//                .transform(transformation)
                .into(target);

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
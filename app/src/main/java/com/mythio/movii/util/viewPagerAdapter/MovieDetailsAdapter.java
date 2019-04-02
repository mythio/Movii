package com.mythio.movii.util.viewPagerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieTmdb;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieDetailsAdapter extends PagerAdapter {

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
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_movie_detail, null);

        TextView tv = view.findViewById(R.id.text_view_title_1);
        tv.setText(movie.getTitle());
        tv.setVisibility(View.VISIBLE);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        Log.d("TAG_TAG_TAG", "Inflating " + movie.getTitle());

        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}

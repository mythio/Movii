package com.mythio.movii.util.viewPagerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.contract.fragment.discover.baseFragmentDiscover.OnItemClickListener;
import com.mythio.movii.model.movie.MovieTmdb;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieSliderAdapter extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<MovieTmdb> mMovies;
    private OnItemClickListener listener;

    public MovieSliderAdapter(Context mContext, ArrayList<MovieTmdb> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        final MovieTmdb movie = mMovies.get(position);
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        final ImageView im = view.findViewById(R.id.image_view_overlay);
        TextView textViewTitle1 = view.findViewById(R.id.text_view_title1);
        TextView textViewTitle2 = view.findViewById(R.id.text_view_title2);

        im.setImageDrawable(mContext.getDrawable(R.drawable.bg_gradient_movie));

        String[] title_arr = movie.getTitle().split(": ");

        if (title_arr.length == 2) {
            textViewTitle1.setText(title_arr[0].trim());
            textViewTitle2.setVisibility(View.VISIBLE);
            textViewTitle2.setText(title_arr[1].trim());
        } else {
            textViewTitle1.setText(title_arr[0].trim());
            textViewTitle2.setVisibility(View.GONE);
        }

        String url = IMAGE_BASE_URL + "w780" + movie.getBackdropPath();

        Picasso.get()
                .load(url)
                .into(imageViewBackdrop);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movie.getId());
            }
        });

        Log.v("TAG_TAG_TAG", movie.getTitle() + "");

        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
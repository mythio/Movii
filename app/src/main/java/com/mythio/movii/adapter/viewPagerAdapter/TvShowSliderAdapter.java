package com.mythio.movii.adapter.viewPagerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.mythio.movii.R;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class TvShowSliderAdapter extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<TvShowTmdb> tvShows;
    private final ItemClickListener.OnItemClick listener;

    public TvShowSliderAdapter(Context mContext, ArrayList<TvShowTmdb> tvShows, ItemClickListener.OnItemClick listener) {
        this.mContext = mContext;
        this.tvShows = tvShows;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int i) {
        final TvShowTmdb tvShow = tvShows.get(i);

        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        TextView textViewTitle1 = view.findViewById(R.id.text_view_title1);
        TextView textViewTitle2 = view.findViewById(R.id.text_view_title2);

        textViewTitle1.setText(tvShow.getName());
        textViewTitle2.setVisibility(View.GONE);

        String url = IMAGE_BASE_URL + "w780" + tvShow.getBackdropPath();

        Glide.with(App.getContext())
                .load(url)
                .into(imageViewBackdrop);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        view.setOnClickListener(v -> listener.onItemClick(tvShow.getId()));

        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.TvShow;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class TvShowSliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<TvShow> mTvShows;

    public TvShowSliderAdapter(Context mContext, List<TvShow> mTvShows) {
        this.mContext = mContext;
        this.mTvShows = mTvShows;
//        Collections.sort(mSeries, new Comparator<TvShow>() {
//            @Override
//            public int compare(TvShow o1, TvShow o2) {
//                return o2.getImdbRatings().compareTo(o1.getImdbRatings());
//            }
//        });
    }

    @Override
    public int getCount() {
        return mTvShows.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int i) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TvShow tvShow = mTvShows.get(i);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        ImageView im = view.findViewById(R.id.image_view_overlay);
        TextView textViewName = view.findViewById(R.id.text_view_title1);
        TextView textViewRating = view.findViewById(R.id.text_view_imdb_rating);

        im.setImageDrawable(mContext.getDrawable(R.drawable.bg_gradient_tv));

        textViewRating.setText(tvShow.getImdbRatings());
        textViewName.setText(tvShow.getName());

        String url = TMDB_IMAGE + "w780" + tvShow.getBackdrop();

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
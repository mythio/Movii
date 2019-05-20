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

    @BindView(R.id.txt_view_overview)
    TextView txtViewOverview;

    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;

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
        View view = inflater.inflate(R.layout.item_tvshow_details, null);
        ButterKnife.bind(this, view);

        txtViewTitle1.setText(tvShow.getName());
        txtViewTitle2.setText(season.getName());
        Picasso.get().load(IMAGE_BASE_URL + "w780" + season.getPosterPath()).into(imgViewPoster);

        txtViewOverview.setText(season.getOverview());

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

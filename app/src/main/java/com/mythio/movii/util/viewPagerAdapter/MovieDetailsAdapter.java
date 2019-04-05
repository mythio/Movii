package com.mythio.movii.util.viewPagerAdapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.movie.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieDetailsAdapter extends PagerAdapter {

    @BindView(R.id.img_btn_close)
    ImageButton imgBtnClose;

    @BindView(R.id.img_view_play)
    ImageButton imgBtnPlay;

    @BindView(R.id.img_view_grad_bg)
    ImageView imgViewGradientBg;

    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;

    @BindView(R.id.txt_view_title_1)
    TextView txtViewTitle1;

    @BindView(R.id.txt_view_title_2)
    TextView txtViewTitle2;

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

        final View view = inflater.inflate(R.layout.item_movie_details, null);

        ButterKnife.bind(this, view);

//        TextView tv = view.findViewById(R.id.text_view_title_1);
//        final ImageView iv = view.findViewById(R.id.image_view_poster);
//        final ConstraintLayout cs = view.findViewById(R.id.container);

//        tv.setText(movie.getTitle());
//        tv.setVisibility(View.VISIBLE);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

//        final ImageView im2 = view.findViewById(R.id.bggg);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable Palette palette) {
//                        if (palette.getDarkMutedSwatch() != null) {
//                            cs.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
//                            im2.setImageTintList(ColorStateList.valueOf(palette.getDarkMutedSwatch().getRgb()));
//                        } else {
//                            cs.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
//                            im2.setImageTintList(ColorStateList.valueOf(palette.getDarkVibrantSwatch().getRgb()));
//                        }
                    }
                });

//                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get().load(IMAGE_BASE_URL + "original" + movie.getPosterPath()).into(target);

        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}

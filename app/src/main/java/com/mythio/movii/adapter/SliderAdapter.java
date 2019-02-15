package com.mythio.movii.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mythio.movii.R;
import com.mythio.movii.model.Rounded;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.util.List;
import java.util.Objects;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<String> url;

    public SliderAdapter(Context context, List<String> url) {
        this.context = context;
        this.url = url;
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider, null);
        ImageView imageView = view.findViewById(R.id.img);
        ImageView bg = view.findViewById(R.id.bg);

        Transformation transformation = new Rounded(32, Rounded.Corners.ALL);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(bitmap);

                Palette.from(bitmap)
                        .generate(palette -> {
                            Palette.Swatch textSwatch = Objects.requireNonNull(palette).getVibrantSwatch();
                            bg.setImageTintList(ColorStateList.valueOf(Objects.requireNonNull(textSwatch).getRgb()));
                        });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get()
                .load(url.get(position))
                .transform(transformation)
                .into(target);

        Log.d("TAG_TAG_TAG", url.get(position));

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
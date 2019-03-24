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
import com.mythio.movii.fragment.moviesFragment.OnItemClickListener;
import com.mythio.movii.model.movie.Movie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieSliderAdapter extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<Movie> movies;
    private OnItemClickListener listener;

    public MovieSliderAdapter(Context mContext, ArrayList<Movie> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
    public Object instantiateItem(@NonNull ViewGroup container, int i) {
        final Movie movie = movies.get(i);

        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        ImageView im = view.findViewById(R.id.image_view_overlay);
        TextView textViewTitle1 = view.findViewById(R.id.text_view_title1);
        TextView textViewTitle2 = view.findViewById(R.id.text_view_title2);
        TextView textViewRating = view.findViewById(R.id.text_view_imdb_rating);

        im.setImageDrawable(mContext.getDrawable(R.drawable.bg_gradient_movie));

        view.findViewById(R.id.play_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movie);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MovieActivity.class);
//                intent.putExtra("MOVIE_ACTIVITY", movie);
//                mContext.startActivity(intent);
            }
        });

        textViewRating.setText(movie.getRating());

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
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
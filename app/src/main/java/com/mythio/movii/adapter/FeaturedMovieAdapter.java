package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;

import java.util.List;

public class FeaturedMovieAdapter extends RecyclerView.Adapter<FeaturedMovieAdapter.FeaturedMovieHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public FeaturedMovieAdapter(Context mContext, List<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;

        for (int i = 0; i < mMovies.size(); ++i) {
            Log.d("tag_aafsfa", mMovies.get(i).getTitle1());
        }
    }

    @NonNull
    @Override
    public FeaturedMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cast_featured, viewGroup, false);
        return new FeaturedMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedMovieHolder featuredMovieHolder, int i) {
        Movie movie = mMovies.get(i);

        if (movie.getTitle2().equals("")) {
            featuredMovieHolder.castFeaturedTitle1.setText(movie.getTitle1());
            featuredMovieHolder.castFeaturedTitle2.setVisibility(View.GONE);
        } else {
            featuredMovieHolder.castFeaturedTitle1.setText(movie.getTitle1());
            featuredMovieHolder.castFeaturedTitle2.setVisibility(View.VISIBLE);
            featuredMovieHolder.castFeaturedTitle2.setText(movie.getTitle2());
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class FeaturedMovieHolder extends RecyclerView.ViewHolder {

        public TextView castFeaturedTitle1;
        public TextView castFeaturedTitle2;

        public FeaturedMovieHolder(@NonNull View itemView) {
            super(itemView);

            castFeaturedTitle1 = itemView.findViewById(R.id.text_view_cast_featured_title1);
            castFeaturedTitle2 = itemView.findViewById(R.id.text_view_cast_featured_title2);
        }
    }
}

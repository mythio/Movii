package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mythio.movii.R;
import com.mythio.movii.model.Movie;

import java.util.ArrayList;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.MovieHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovieList;

    public SimilarMovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Movie movie = mMovieList.get(i);
        movieHolder.mTextViewTitle.setText(movie.getTitle1());
        String url = "https://image.tmdb.org/t/p/w500";
        url += movie.getPoster_path();
        Glide.with(mContext).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(movieHolder.mImageViewPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPoster;
        private TextView mTextViewTitle;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            mImageViewPoster = itemView.findViewById(R.id.list_image_movie);
            mTextViewTitle = itemView.findViewById(R.id.list_text_view_title);
        }
    }
}
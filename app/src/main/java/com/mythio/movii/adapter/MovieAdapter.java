package com.mythio.movii.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.activity.MovieActivity;
import com.mythio.movii.model.Movie;
import com.mythio.movii.model.Rounded;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import static com.mythio.movii.constant.constants.TMDB_IMAGE;
import static com.mythio.movii.model.Rounded.Corners.ALL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mMovies;
    private Context mContext;

    public MovieAdapter(Context mContext, List<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movies, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Movie movie = mMovies.get(i);

        if (movie.getTitle2().equals("")) {
            movieHolder.mTextViewTitle1.setText(movie.getTitle1());
            movieHolder.mTextViewTitle2.setVisibility(View.GONE);
        } else {
            movieHolder.mTextViewTitle1.setText(movie.getTitle1());
            movieHolder.mTextViewTitle2.setVisibility(View.VISIBLE);
            movieHolder.mTextViewTitle2.setText(movie.getTitle2());
        }

        movieHolder.mTextViewGenre.setText(movie.getGenre());
        movieHolder.mRatingBar.setRating((float) 4.5);
        String url = TMDB_IMAGE + "w154" + movie.getPoster_path();

        Transformation transformation = new Rounded(16, ALL);


        Picasso.get().load(url)
                .fit()
                .transform(transformation)
                .into(movieHolder.mImageViewPoster);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewPoster;
        private TextView mTextViewTitle1;
        private TextView mTextViewTitle2;
        private TextView mTextViewGenre;
        private RatingBar mRatingBar;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            mImageViewPoster = itemView.findViewById(R.id.image_view_poster);
            mTextViewTitle1 = itemView.findViewById(R.id.text_view_title_1);
            mTextViewTitle2 = itemView.findViewById(R.id.text_view_title_2);
            mTextViewGenre = itemView.findViewById(R.id.text_view_genre);
            mRatingBar = itemView.findViewById(R.id.rating_bar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = mMovies.get(getAdapterPosition());
            Intent intent = new Intent(mContext, MovieActivity.class);
            intent.putExtra("EXTRA", movie);
            mContext.startActivity(intent);
        }
    }
}
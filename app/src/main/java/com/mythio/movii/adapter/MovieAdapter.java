package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_movie_layout, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
//        Movie movie = mMovies.get(i);
//        movieHolder.mTextViewTitle.setText(movie.getTitle());
//        movieHolder.mTextViewGenre.setText("asda");
//        movieHolder.mRatingBar.setRating(5);

//        String url = "https://image.tmdb.org/t/p/w300";
//        url += movie.getPoster_path();

//        Picasso.get().load(url).into(movieHolder.mImageViewPoster);

//        Log.d("LOG_LOG", movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
//        private ImageView mImageViewPoster;
//        private TextView mTextViewTitle;
//        private TextView mTextViewGenre;
//        private RatingBar mRatingBar;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);

//            mImageViewPoster = itemView.findViewById(R.id.image_view_poster);
//            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
//            mTextViewGenre = itemView.findViewById(R.id.text_view_genre);
//            mRatingBar = itemView.findViewById(R.id.rating_bar);

        }
    }
}

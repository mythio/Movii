package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;

import java.util.List;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.MovieSearchHolder> {
    private Context mContext;
    private List<Movie> mMovies;

    public MovieSearchAdapter(Context mContext, List mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public MovieSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieSearchHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchHolder movieSearchHolder, int i) {
        Movie movie = mMovies.get(i);

        if (movie.getTitle2().equals("")) {
            movieSearchHolder.textViewTitle1.setText(movie.getTitle1());
            movieSearchHolder.textViewTitle2.setVisibility(View.GONE);
        } else {
            movieSearchHolder.textViewTitle1.setText(movie.getTitle1());
            movieSearchHolder.textViewTitle2.setVisibility(View.VISIBLE);
            movieSearchHolder.textViewTitle2.setText(movie.getTitle2());
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieSearchHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle1;
        private TextView textViewTitle2;

        public MovieSearchHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle1 = itemView.findViewById(R.id.text_view_title1);
            textViewTitle2 = itemView.findViewById(R.id.text_view_title2);
        }
    }
}

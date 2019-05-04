package com.mythio.movii.adapter.recyclerViewAdapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.MovieSearchHolder> {
    private Context mContext;
    private ArrayList<MovieTmdb> mMovies;

    public MovieSearchAdapter(Context mContext, ArrayList<MovieTmdb> mMovies) {
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
        MovieTmdb movie = mMovies.get(i);

        String[] date = movie.getReleaseDate().split("-");

        movieSearchHolder.textViewTitle.setText(movie.getTitle());
        movieSearchHolder.textViewYear.setText(date[0]);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ItemDecorator extends RecyclerView.ItemDecoration {

        private final int space;

        public ItemDecorator(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    public class MovieSearchHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewYear;

        public MovieSearchHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewYear = itemView.findViewById(R.id.text_view_year);
        }
    }
}

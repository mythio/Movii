package com.mythio.movii.adapter.recyclerViewAdapter.SearchMovie;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.Discover.fragment.contract.OnItemClickListener;
import com.mythio.movii.adapter.recyclerViewAdapter.Contract;
import com.mythio.movii.model.movie.MovieTmdb;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {
    private final SearchMoviePresenter presenter;
    private final OnItemClickListener listener;

    public SearchMovieAdapter(SearchMoviePresenter presenter, OnItemClickListener listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchMovieViewHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_search_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder viewHolder, int i) {
        presenter.onBindViewAtPosition(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class SearchMovieViewHolder extends RecyclerView.ViewHolder implements Contract.View<MovieTmdb> {
        private final TextView textViewTitle;
        private final TextView textViewYear;

        SearchMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewYear = itemView.findViewById(R.id.text_view_year);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }

        @Override
        public void show(@NonNull MovieTmdb movie) {
            String[] date = movie.getReleaseDate().split("-");
            textViewTitle.setText(movie.getTitle());
            textViewYear.setText(date[0]);
        }
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
}

package com.mythio.movii.adapter.recycler_view_adapter.search_movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.adapter.recycler_view_adapter.Contract;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {
    private final SearchMoviePresenter presenter;
    private final ItemClickListener.OnItemClick listener;

    public SearchMovieAdapter(SearchMoviePresenter presenter, ItemClickListener.OnItemClick listener) {
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

    public class SearchMovieViewHolder extends RecyclerView.ViewHolder implements Contract.View<Movie> {
        @BindView(R.id.tv_title)
        TextView textViewTitle;

        @BindView(R.id.tv_year)
        TextView textViewYear;

        SearchMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }

        @Override
        public void show(@NonNull Movie movie) {
            String[] date = movie.getReleaseDate().split("-");
            textViewTitle.setText(movie.getTitle());
            textViewYear.setText(date[0]);
        }
    }
}

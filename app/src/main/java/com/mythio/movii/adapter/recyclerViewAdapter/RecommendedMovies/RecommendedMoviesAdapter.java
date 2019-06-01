package com.mythio.movii.adapter.recyclerViewAdapter.RecommendedMovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.Discover.fragment.contract.OnItemClickListener;
import com.mythio.movii.adapter.recyclerViewAdapter.Contract;
import com.mythio.movii.model.movie.MovieTmdb;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class RecommendedMoviesAdapter extends RecyclerView.Adapter<RecommendedMoviesAdapter.RecommendedMovieViewHolder> {
    private final RecommendedMoviesPresenter presenter;
    private final OnItemClickListener listener;

    public RecommendedMoviesAdapter(RecommendedMoviesPresenter presenter, OnItemClickListener listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecommendedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecommendedMovieViewHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_recommended, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedMovieViewHolder viewHolder, int i) {
        presenter.onBindViewAtPosition(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    class RecommendedMovieViewHolder extends RecyclerView.ViewHolder implements Contract.View<MovieTmdb> {
        @BindView(R.id.img_view_poster)
        ImageView mImageViewPoster;

        @BindView(R.id.txt_view_title)
        TextView mTextViewTitle;

        RecommendedMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }

        @Override
        public void show(@NonNull MovieTmdb movieTmdb) {
            Picasso.get()
                    .load(IMAGE_BASE_URL + "w154" + movieTmdb.getPosterPath())
                    .into(mImageViewPoster);
            mTextViewTitle.setText(movieTmdb.getTitle());
        }
    }
}
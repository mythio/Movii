package com.mythio.movii.adapter.popularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mythio.movii.R;
import com.mythio.movii.adapter.Contract;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constants.BASE_URL_IMAGE;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder> {
    private final Contract.Presenter<Movie> mPresenter;
    private final ItemClickListener.OnItemClick mListener;

    public PopularMovieAdapter(Contract.Presenter<Movie> presenter, ItemClickListener.OnItemClick listener) {
        this.mPresenter = presenter;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularMovieViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_slideshow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieViewHolder viewHolder, int position) {
        mPresenter.onBindViewAtPosition(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    class PopularMovieViewHolder extends RecyclerView.ViewHolder implements Contract.View<Movie> {

        @BindView(R.id.iv_backdrop)
        ImageView ivBackdrop;

        @BindView(R.id.tv_title1)
        TextView tvTitle1;

        @BindView(R.id.tv_title2)
        TextView tvTitle2;

        public PopularMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void show(Movie movie) {
            String[] title_arr = movie.getTitle().split(": ");

            if (title_arr.length == 2) {
                tvTitle1.setText(title_arr[0].trim());
                tvTitle2.setVisibility(View.VISIBLE);
                tvTitle2.setText(title_arr[1].trim());
            } else {
                tvTitle1.setText(title_arr[0].trim());
                tvTitle2.setVisibility(View.GONE);
            }

            String url = BASE_URL_IMAGE + "w780" + movie.getBackdropPath();

            Glide.with(App.getContext())
                    .load(url)
                    .into(ivBackdrop);

            itemView.setOnClickListener(v -> mListener.onItemClick(getAdapterPosition()));
        }
    }
}

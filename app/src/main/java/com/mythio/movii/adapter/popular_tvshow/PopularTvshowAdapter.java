package com.mythio.movii.adapter.popular_tvshow;

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
import com.mythio.movii.model.tvshow.TvShow;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constants.BASE_URL_IMAGE;

public class PopularTvshowAdapter extends RecyclerView.Adapter<PopularTvshowAdapter.PopularTvShowViewHolder> {
    private final Contract.Presenter<TvShow> mPresenter;
    private final ItemClickListener.OnItemClick mListener;

    public PopularTvshowAdapter(Contract.Presenter<TvShow> presenter, ItemClickListener.OnItemClick listener) {
        this.mPresenter = presenter;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PopularTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularTvShowViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_slideshow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTvShowViewHolder viewHolder, int position) {
        mPresenter.onBindViewAtPosition(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    class PopularTvShowViewHolder extends RecyclerView.ViewHolder implements Contract.View<TvShow> {

        @BindView(R.id.iv_backdrop)
        ImageView ivBackdrop;

        @BindView(R.id.tv_title1)
        TextView tvTitle1;

        public PopularTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void show(TvShow tvShow) {
            tvTitle1.setText(tvShow.getName());
            String url = BASE_URL_IMAGE + "w780" + tvShow.getBackdropPath();

            Glide.with(App.getContext())
                    .load(url)
                    .into(ivBackdrop);
        }
    }
}

package com.mythio.movii.adapter.slideshow_tvshow;

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
import com.mythio.movii.model.tv_show.TvShow;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class SlideshowTvshowAdapter extends RecyclerView.Adapter<SlideshowTvshowAdapter.SlideshowTvShowViewHolder> {
    private final Contract.Presenter<TvShow> mPresenter;
    private final ItemClickListener.OnItemClick mListener;

    public SlideshowTvshowAdapter(Contract.Presenter<TvShow> presenter, ItemClickListener.OnItemClick listener) {
        this.mPresenter = presenter;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SlideshowTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideshowTvShowViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_slideshow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideshowTvShowViewHolder viewHolder, int position) {
        mPresenter.onBindViewAtPosition(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    class SlideshowTvShowViewHolder extends RecyclerView.ViewHolder implements Contract.View<TvShow> {

        @BindView(R.id.iv_backdrop)
        ImageView ivBackdrop;

        @BindView(R.id.tv_title1)
        TextView tvTitle1;

//        @BindView(R.id.tv_title2)
//        TextView tvTitle2;

        public SlideshowTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void show(TvShow tvShow) {
            tvTitle1.setText(tvShow.getName());
            String url = IMAGE_BASE_URL + "w780" + tvShow.getBackdropPath();

            Glide.with(App.getContext())
                    .load(url)
                    .into(ivBackdrop);
        }
    }
}

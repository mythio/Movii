package com.mythio.movii.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.TvShow;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvShowSearchAdapter extends RecyclerView.Adapter<TvShowSearchAdapter.TvShowSearchHolder> {
    private Context mContext;
    private List<TvShow> mTvShows;

    public TvShowSearchAdapter(Context mContext, List<TvShow> mTvShows) {
        this.mContext = mContext;
        this.mTvShows = mTvShows;
    }

    @NonNull
    @Override
    public TvShowSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TvShowSearchHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowSearchHolder tvShowSearchHolder, int i) {
        TvShow tvShow = mTvShows.get(i);

        tvShowSearchHolder.textViewTitle1.setText(tvShow.getName());
        tvShowSearchHolder.textViewTitle2.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public class TvShowSearchHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle1;
        private TextView textViewTitle2;

        public TvShowSearchHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle1 = itemView.findViewById(R.id.text_view_title1);
            textViewTitle2 = itemView.findViewById(R.id.text_view_title2);
        }
    }
}

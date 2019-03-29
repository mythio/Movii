package com.mythio.movii.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class TvShowSearchAdapter extends RecyclerView.Adapter<TvShowSearchAdapter.TvShowSearchHolder> {
    private Context mContext;
    private ArrayList<TvShowTmdb> mTvShows;

    public TvShowSearchAdapter(Context mContext, ArrayList<TvShowTmdb> tvShows) {
        this.mContext = mContext;
        this.mTvShows = tvShows;
    }

    @NonNull
    @Override
    public TvShowSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TvShowSearchHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowSearchHolder movieSearchHolder, int i) {
        TvShowTmdb tvShow = mTvShows.get(i);

        movieSearchHolder.textViewTitle.setText(tvShow.getName());
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
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

    public class TvShowSearchHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;

        public TvShowSearchHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
        }
    }
}

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
import com.mythio.movii.contract.fragment.discover.baseFragmentDiscover.OnItemClickListener;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class TvShowSearchAdapter extends RecyclerView.Adapter<TvShowSearchAdapter.TvShowSearchHolder> {
    private Context mContext;
    private ArrayList<TvShowTmdb> mTvShows;
    private OnItemClickListener listener;

    public TvShowSearchAdapter(Context mContext, ArrayList<TvShowTmdb> tvShows, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mTvShows = tvShows;
        this.listener = listener;
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

            itemView.setOnClickListener(v -> listener.onItemClick(mTvShows.get(getAdapterPosition()).getId()));
        }
    }
}

package com.mythio.movii.adapter.recyclerViewAdapter.SearchTvShow;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.Discover.fragment.contract.OnItemClickListener;
import com.mythio.movii.adapter.recyclerViewAdapter.SearchTvShow.contract.Contract;
import com.mythio.movii.adapter.recyclerViewAdapter.SearchTvShow.contract.SearchTvShowPresenter;
import com.mythio.movii.model.tvShow.TvShowTmdb;

public class SearchTvShowAdapter extends RecyclerView.Adapter<SearchTvShowAdapter.SearchTvShowViewHolder> {
    private final SearchTvShowPresenter presenter;
    private final OnItemClickListener listener;

    public SearchTvShowAdapter(SearchTvShowPresenter presenter, OnItemClickListener listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchTvShowViewHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_search_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTvShowViewHolder viewHolder, int i) {
        presenter.onBindViewAtPosition(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class SearchTvShowViewHolder extends RecyclerView.ViewHolder implements Contract.View {

        private final TextView textViewTitle;

        SearchTvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }


        @Override
        public void show(TvShowTmdb tvShowTmdb) {
            textViewTitle.setText(tvShowTmdb.getName());
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

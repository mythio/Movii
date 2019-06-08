package com.mythio.movii.adapter.recycler_view_adapter.search_tv_show;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.adapter.recycler_view_adapter.Contract;
import com.mythio.movii.model.tv_show.TvShowTmdb;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvShowAdapter extends RecyclerView.Adapter<SearchTvShowAdapter.SearchTvShowViewHolder> {
    private final SearchTvShowPresenter presenter;
    private final ItemClickListener.OnItemClick listener;

    public SearchTvShowAdapter(SearchTvShowPresenter presenter, ItemClickListener.OnItemClick listener) {
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

    public class SearchTvShowViewHolder extends RecyclerView.ViewHolder implements Contract.View<TvShowTmdb> {
        @BindView(R.id.tv_title)
        TextView textViewTitle;

        SearchTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }

        @Override
        public void show(@NonNull TvShowTmdb tvShowTmdb) {
            textViewTitle.setText(tvShowTmdb.getName());
        }
    }
}

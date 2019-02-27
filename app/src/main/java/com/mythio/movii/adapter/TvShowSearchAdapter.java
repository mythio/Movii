package com.mythio.movii.adapter;

import android.content.Context;
import android.graphics.Rect;
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

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

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

        Picasso.get().load(TMDB_IMAGE + "w185" + tvShow.getPoster_path()).into(tvShowSearchHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public class TvShowSearchHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle1;
        private TextView textViewTitle2;
        private ImageView imageView;

        public TvShowSearchHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle1 = itemView.findViewById(R.id.text_view_title1);
            textViewTitle2 = itemView.findViewById(R.id.text_view_title2);
            imageView = itemView.findViewById(R.id.poster);
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

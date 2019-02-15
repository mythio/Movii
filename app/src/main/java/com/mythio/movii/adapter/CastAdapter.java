package com.mythio.movii.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
import com.mythio.movii.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {
    private List<Person> mCasts;
    private Context mContext;
    private final ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onClick(View view, int position);
    }

    public CastAdapter(List<Person> mCasts, Context mContext, ListItemClickListener mOnClickListener) {
        this.mCasts = mCasts;
        this.mContext = mContext;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_cast, viewGroup, false);
        return new CastHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder castHolder, int i) {

        String url = TMDB_IMAGE + "w185" + mCasts.get(i).getProfile_path();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.movie_placeholder)
                .into(castHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    class CastHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView imageView;

        CastHolder(@NonNull View itemView, ListItemClickListener mOnClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(v, getAdapterPosition());
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
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.left = space;
            }
        }
    }
}
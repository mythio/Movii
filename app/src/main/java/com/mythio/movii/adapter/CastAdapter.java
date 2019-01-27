package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mythio.movii.R;

import java.util.List;

import static com.mythio.movii.constant.constants.TMDB_IMAGE;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {
    private List<String> cast;
    private Context mContext;

    public CastAdapter(List<String> cast, Context mContext) {
        this.cast = cast;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_cast, viewGroup, false);
        return new CastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CastHolder castHolder, int i) {
        String url = TMDB_IMAGE + "w185" + cast.get(i);
//        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator());
//        fadeIn.setDuration(100);
//        fadeIn.reset();
//        castHolder.imageView.startAnimation(fadeIn);
        Glide.with(mContext).load(url).apply(RequestOptions.circleCropTransform()).transition(DrawableTransitionOptions.withCrossFade()).into(castHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    class CastHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        CastHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast);
        }
    }
}
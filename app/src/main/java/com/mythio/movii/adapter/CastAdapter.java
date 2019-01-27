package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.mythio.movii.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    public void onBindViewHolder(@NonNull CastHolder castHolder, int i) {
        String url = TMDB_IMAGE + "w185" + cast.get(i);
        Picasso.get().load(url).into(castHolder.imageView);
        castHolder.imageView.startAnimation(castHolder.fadeIn);
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    class CastHolder extends RecyclerView.ViewHolder {

        private Animation fadeIn;
        private CircleImageView imageView;

        CastHolder(@NonNull View itemView) {
            super(itemView);
            fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setDuration(200);
            fadeIn.reset();
            imageView = itemView.findViewById(R.id.cast);
        }
    }
}
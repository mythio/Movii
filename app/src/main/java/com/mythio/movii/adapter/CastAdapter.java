package com.mythio.movii.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
        Picasso.get().load(url).into(castHolder.target);
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    class CastHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        Target target;

        CastHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast);
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(bitmap);
                    imageView.setAlpha(0f);
                    imageView.animate().setDuration(1000).alpha(1f).start();
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
        }
    }
}
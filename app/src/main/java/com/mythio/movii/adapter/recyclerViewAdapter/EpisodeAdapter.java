package com.mythio.movii.adapter.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.model.episode.Episode;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {

    private final Context mContext;
    private final ArrayList<Episode> mEpisodes;

    public EpisodeAdapter(Context mContext, ArrayList<Episode> mEpisodes) {
        this.mContext = mContext;
        this.mEpisodes = mEpisodes;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_exp_tv_episode, parent, false);
        return new EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, int position) {
        Episode episode = mEpisodes.get(position);

        holder.textViewOverview.setText(episode.getOverview());
        holder.textViewEpisodeName.setText(episode.getName());
    }

    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }

    class EpisodeHolder extends RecyclerView.ViewHolder {

        private final TextView textViewEpisodeName;
        private final TextView textViewOverview;
        private final ImageView imageViewStill;

        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);

            textViewEpisodeName = itemView.findViewById(R.id.txt_view_ep_name);
            textViewOverview = itemView.findViewById(R.id.txt_view_overview);
            imageViewStill = itemView.findViewById(R.id.img_view_still);
        }
    }
}

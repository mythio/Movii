package com.mythio.movii.adapter.recycler_view_adapter.Cast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mythio.movii.R;
import com.mythio.movii.adapter.recycler_view_adapter.Contract;
import com.mythio.movii.model.cast.Cast;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private final CastPresenter presenter;
    private final ItemClickListener.OnItemClickData<ImageView> listener;

    public CastAdapter(CastPresenter presenter, ItemClickListener.OnItemClickData<ImageView> listener) {
        this.presenter = presenter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CastViewHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_cast, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder viewHolder, int i) {
        presenter.onBindViewAtPosition(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    class CastViewHolder extends RecyclerView.ViewHolder implements Contract.View<Cast> {
        @BindView(R.id.cast)
        CircleImageView imageView;

        CastViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition(), imageView));
        }

        @Override
        public void show(@NonNull Cast cast) {
            Glide.with(App.getContext())
                    .load(IMAGE_BASE_URL + "w185" + cast.getProfilePath())
                    .into(imageView);
        }
    }
}
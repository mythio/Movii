package com.mythio.movii.util.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.model.movie.MovieTmdb;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class RecommendedMoviesAdapter extends RecyclerView.Adapter<RecommendedMoviesAdapter.MovieHolder> {

    private Context mContext;
    private ArrayList<MovieTmdb> mMovieList;

    public RecommendedMoviesAdapter(Context mContext, ArrayList<MovieTmdb> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommended, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        MovieTmdb movie = mMovieList.get(i);
        movieHolder.mTextViewTitle.setText(movie.getTitle());

        String url = IMAGE_BASE_URL + "w154" + movie.getPosterPath();

        Picasso.get()
                .load(url)
                .into(movieHolder.mImageViewPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPoster;
        private TextView mTextViewTitle;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            mImageViewPoster = itemView.findViewById(R.id.img_view_poster);
            mTextViewTitle = itemView.findViewById(R.id.txt_view_title);
        }
    }

/*    public static class ItemDecorator extends RecyclerView.ItemDecoration {

        private final int space;

        public ItemDecorator(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.right = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = space;
            }
        }
    }*/
}
package com.mythio.movii.activity.movie_details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mythio.movii.R;
import com.mythio.movii.activity.movie_details.dialog_contract.Contract;
import com.mythio.movii.activity.movie_details.dialog_contract.Presenter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.recommended_movies.RecommendedMoviesPresenter;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class CastBottomDialog extends BottomSheetDialogFragment implements Contract.View {

    @BindView(R.id.cast)
    CircleImageView imageView;

    @BindView(R.id.txt_view_cast)
    TextView textViewCast;

    @BindView(R.id.txt_view_character)
    TextView textViewCharacter;

    @BindView(R.id.recycler_view_related)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_cast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Presenter presenter = new Presenter(this);

        Bundle bundle = getArguments();
        String url = bundle.getString("123");

        ImageView imageView = view.findViewById(R.id.cast);

        int id = bundle.getInt("int");

        presenter.onLaunch(id);

        textViewCast.setText(bundle.getString("234"));
        textViewCharacter.setText("as " + bundle.getString("345"));

        Glide.with(App.getContext())
                .load(IMAGE_BASE_URL + "w185" + url)
                .into(imageView);
    }

    @Override
    public void initSheet(ArrayList<MovieTmdb> movies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new ItemDecorator(24, ItemDecorator.HORIZONTAL));

        RecommendedMoviesPresenter recommendedMoviesPresenter = new RecommendedMoviesPresenter(movies);

        RecommendedMoviesAdapter recommendedMoviesAdapter = new RecommendedMoviesAdapter(recommendedMoviesPresenter, null);
        recyclerView.setAdapter(recommendedMoviesAdapter);
        Log.d("TAG_TAG_TAG", movies.size() + " ");
    }
}
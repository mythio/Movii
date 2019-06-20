package com.mythio.movii.activity.moviedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mythio.movii.R;
import com.mythio.movii.activity.moviedetails.castdialogcontract.Contract;
import com.mythio.movii.activity.moviedetails.castdialogcontract.Presenter;
import com.mythio.movii.adapter.recommendationmovies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recommendationmovies.RecommendedMoviesPresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.App;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.util.Constants.BASE_URL_IMAGE;

public class CastBottomDialog extends BottomSheetDialogFragment implements Contract.View {
//
//    @BindView(R.id.v_bg)
//    View bg;

    @BindView(R.id.iv_cast_profile)
    CircleImageView imageView;

    @BindView(R.id.tv_name)
    TextView textViewCast;

    @BindView(R.id.tv_character)
    TextView textViewCharacter;

    @BindView(R.id.rv_recommended)
    RecyclerView recyclerView;

    private Contract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_cast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setPresenter(new Presenter(this));

        mPresenter.onViewInitialized();
        mPresenter.getData(Objects.requireNonNull(getArguments()).getInt("int"));
    }

    @Override
    public void showInitView() {
        Bundle bundle = getArguments();

        String profilePath = Objects.requireNonNull(bundle).getString("123");
        String castName = Objects.requireNonNull(bundle).getString("234");
        String characterName = Objects.requireNonNull(bundle).getString("345");

        textViewCast.setText(castName);
        textViewCharacter.setText(characterName);
//        bg.setBackgroundTintList(ColorStateList.valueOf(bundle.getInt("color")));

        Glide.with(App.getContext())
                .load(BASE_URL_IMAGE + "w185" + profilePath)
                .into(imageView);
    }

    @Override
    public void showRecommendedMovies(ArrayList<Movie> movies) {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new ItemDecorator(24, ItemDecorator.HORIZONTAL));

        RecommendedMoviesPresenter recommendedMoviesPresenter = new RecommendedMoviesPresenter(movies);

        RecommendedMoviesAdapter recommendedMoviesAdapter = new RecommendedMoviesAdapter(recommendedMoviesPresenter, null);
        recyclerView.setAdapter(recommendedMoviesAdapter);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
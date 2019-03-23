package com.mythio.movii.fragment.moviesFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends BaseFragment implements MoviesFragmentContract.View, MoviesFragmentContract.Callback {

    @BindView(R.id.view_pager_popular)
    ViewPager viewPager;

    private MoviesFragmentContract.Presenter presenter;
    private List<Movie> movies = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter = new MoviesFragmentPresenter(this);
        presenter.initViews();
        presenter.setDataToViewPager(movies);
    }

    @Override
    public int getLayout() {

        return R.layout.fragment_movies;
    }

    @Override
    public void onDataReceived(List<Movie> movies) {

        presenter.setDataToViewPager(movies);
    }

    @Override
    public void initViewPager() {

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.text_view_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.play_btn).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
                view.findViewById(R.id.text_view_imdb_rating).setAlpha(1.0F - Math.abs(v) * 2);
            }
        });
    }

    @Override
    public void showSlideShow(List<Movie> movies) {

        this.movies = movies;
        viewPager.setAdapter(new MovieSliderAdapter(this.getContext(), movies));
    }
}

package com.mythio.movii.fragment.moviesFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mythio.movii.R;
import com.mythio.movii.activity.searchMovieActivity.SearchMovieActivity;
import com.mythio.movii.activity.youtubeActivity.YouTubePlayerActivity;
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends BaseFragment implements MoviesContract.View,
        MoviesContract.Callback, OnItemClickListener {

    @BindView(R.id.view_pager_popular)
    ViewPager viewPager;

    private MoviesContract.Presenter mPresenter;
    private ArrayList<MovieTmdb> movies;
    private Boolean hasReceived = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new MoviesPresenter(this);
        mPresenter.initViews();

        if (hasReceived) {
            mPresenter.setDataToViewPager(movies);
        }
    }

    @OnClick(R.id.search_btn_go)
    public void send() {

        startActivity(new Intent(getContext(), SearchMovieActivity.class));
    }

    @Override
    public int getLayout() {

        return R.layout.fragment_movies;
    }

    @Override
    public void onDataReceived(ArrayList<MovieTmdb> movies) {
        hasReceived = true;
        this.movies = movies;

        if (mPresenter != null) {
            mPresenter.setDataToViewPager(movies);
        }
    }

    @Override
    public void initViewPager() {

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.text_view_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
            }
        });
    }

    @Override
    public void showSlideShow(ArrayList<MovieTmdb> mMovies) {

        MovieSliderAdapter adapter = new MovieSliderAdapter(getContext(), mMovies);
        adapter.setOnClickListener(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onItemClick(MovieTmdb movie) {

        Intent intent = new Intent(getContext(), YouTubePlayerActivity.class);
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("data", movie.getVideos());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

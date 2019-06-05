package com.mythio.movii.activity.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.discover.fragment.contract.Contract;
import com.mythio.movii.activity.discover.fragment.contract.Presenter;
import com.mythio.movii.activity.movie_details.MovieDetailsActivity;
import com.mythio.movii.activity.search_movie.SearchMovieActivity;
import com.mythio.movii.adapter.view_pager_adapter.MovieSliderAdapter;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends BaseDiscoverFragment implements Contract.View<MovieTmdb>,
        Contract.Callback<MovieTmdb> {

    @BindView(R.id.view_pager_popular)
    ViewPager viewPager;

    private Contract.Presenter<MovieTmdb> mPresenter;
    private ArrayList<MovieTmdb> movies;
    private Boolean hasReceived = false;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new Presenter<>(this);
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
        viewPager.setPageTransformer(false, (view, v) -> {
            view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
            view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
            view.findViewById(R.id.text_view_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
        });
    }

    @Override
    public void showSlideShow(ArrayList<MovieTmdb> mMovies) {
        MovieSliderAdapter adapter = new MovieSliderAdapter(getContext(), mMovies, id -> {
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", String.valueOf(id));
            startActivity(intent);
        });
        viewPager.setAdapter(adapter);
    }
}

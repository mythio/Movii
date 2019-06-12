package com.mythio.movii.activity.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.discover.fragment.contract.Contract;
import com.mythio.movii.activity.discover.fragment.contract.Presenter;
import com.mythio.movii.activity.movie_details.MovieDetailsActivity;
import com.mythio.movii.activity.search_movie.SearchMovieActivity;
import com.mythio.movii.adapter.view_pager_adapter.MovieSliderAdapter;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends BaseDiscoverFragment implements Contract.View<Movie>,
        Contract.Callback<Movie> {

    @BindView(R.id.vp_popular)
    ViewPager viewPager;

    private Contract.Presenter<Movie> mPresenter;
    private ArrayList<Movie> movies;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setPresenter(new Presenter<>(this));

        mPresenter.initViews();
    }

    @OnClick(R.id.search_btn_go)
    void send() {
        startActivity(new Intent(getContext(), SearchMovieActivity.class));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movies;
    }

    @Override
    public void onDataReceived(ArrayList<Movie> movies) {
        this.movies = movies;
        mPresenter.setDataToViewPager(movies);
    }

    @Override
    public void initViewPager() {
        viewPager.setPageTransformer(false, (view, v) -> {
            view.findViewById(R.id.iv_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
            view.findViewById(R.id.tv_title1).setAlpha(1.0F - Math.abs(v) * 2);
            view.findViewById(R.id.tv_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
        });
    }

    @Override
    public void showSlideShow(ArrayList<Movie> movies) {
        MovieSliderAdapter adapter = new MovieSliderAdapter(getContext(), movies, position -> {
            Log.d("TAG_TAG_TAG", position + " returned");
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", movies.get(position).getId());
            startActivity(intent);
        });
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setPresenter(Contract.Presenter<Movie> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

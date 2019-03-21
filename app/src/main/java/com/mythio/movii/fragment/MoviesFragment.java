package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieSliderAdapter;
import com.mythio.movii.contract.MovieFragment.Contract;
import com.mythio.movii.contract.MovieFragment.Presenter;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements Contract.View {

    @BindView(R.id.view_pager_popular)
    ViewPager viewPager;

    private Presenter presenter;

    public MoviesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        presenter = new Presenter(this);
        presenter.requestData();
        presenter.initView(viewPager);

        return view;
    }

    @Override
    public void initSlideShow(final ViewPager viewPager) {
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
        viewPager.setAdapter(new MovieSliderAdapter(MoviesFragment.this.getContext(), movies));
    }
}

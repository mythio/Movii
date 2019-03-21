package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
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

        return view;
    }

    @Override
    public void showText(List<Movie> movies) {
        for (Movie movie : movies) {
            Log.d("TAG_TAG", movie.getRating());
        }
    }
}

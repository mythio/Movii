package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
import com.mythio.movii.contract.MovieFragment.Contract;
import com.mythio.movii.contract.MovieFragment.Presenter;
import com.mythio.movii.model.movie.Movie;

import java.util.List;

public class MoviesFragment extends Fragment implements Contract.View {

    private Presenter presenter;

    public MoviesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);

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

package com.mythio.movii.adapter.recyclerViewAdapter.RecommendedMovies;

import com.mythio.movii.adapter.recyclerViewAdapter.Contract;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class RecommendedMoviesPresenter implements Contract.Presenter<MovieTmdb> {
    private final ArrayList<MovieTmdb> movies;

    public RecommendedMoviesPresenter(ArrayList<MovieTmdb> movies) {
        this.movies = movies;
    }

    @Override
    public void onBindViewAtPosition(Contract.View<MovieTmdb> view, int position) {
        view.show(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}

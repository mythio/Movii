package com.mythio.movii.adapter.slideshow_movies;

import com.mythio.movii.adapter.Contract;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class SlideshowMoviePresenter implements Contract.Presenter<Movie> {
    private final ArrayList<Movie> movies;

    public SlideshowMoviePresenter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void onBindViewAtPosition(Contract.View<Movie> view, int position) {
        view.show(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}

package com.mythio.movii.fragment.moviesFragment;

import com.mythio.movii.model.movie.Movie;

import java.util.List;

public interface ModelCallback {

    void onDataRecieved(List<Movie> movies);
}

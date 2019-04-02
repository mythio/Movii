package com.mythio.movii.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mythio.movii.R;
import com.mythio.movii.contract.activity.movieDetailsActivity.MovieDetailsContract;
import com.mythio.movii.contract.activity.movieDetailsActivity.MovieDetailsPresenter;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MovieDetailsContract.Presenter presenter = new MovieDetailsPresenter(this);

        presenter.onRequestCollection(getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID"));
    }

    @Override
    public void showDetails(ArrayList<Movie> movies) {

        for (Movie movie : movies) {
            Log.d("TAG_TAG", movie.getTitle());
        }
    }
}
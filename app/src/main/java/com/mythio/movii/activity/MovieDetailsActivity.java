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
import com.mythio.movii.util.viewPagerAdapter.MovieDetailsAdapter;
import com.mythio.movii.util.viewPagerAdapter.MovieSliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        viewPager = findViewById(R.id.view_pager_details);
        viewPager.setOffscreenPageLimit(1);

        MovieDetailsContract.Presenter presenter = new MovieDetailsPresenter(this);

        presenter.onRequestCollection(getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID"));
    }

    @Override
    public void showPages(ArrayList<Movie> movies) {

        for (Movie movie : movies) {
            Log.d("TAG_TAG", "123 " + movie.getTitle());
        }

        MovieDetailsAdapter adapter = new MovieDetailsAdapter(this, movies);
//        adapter.setOnClickListener(this);
        viewPager.setAdapter(adapter);
    }
}
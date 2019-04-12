package com.mythio.movii.activity;

import android.os.Bundle;

import com.mythio.movii.R;
import com.mythio.movii.contract.activity.movieDetailsActivity.MovieDetailsContract;
import com.mythio.movii.contract.activity.movieDetailsActivity.MovieDetailsPresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.viewPagerAdapter.MovieDetailsAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    String currentId;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");

        viewPager = findViewById(R.id.view_pager_details);

        MovieDetailsContract.Presenter presenter = new MovieDetailsPresenter(this);

        presenter.onRequestCollection(currentId);
    }

    @Override
    public void showPages(ArrayList<Movie> movies) {

        MovieDetailsAdapter adapter = new MovieDetailsAdapter(this, movies);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }
}
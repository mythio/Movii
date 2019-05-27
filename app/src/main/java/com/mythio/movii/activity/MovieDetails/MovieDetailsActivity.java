package com.mythio.movii.activity.MovieDetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.MovieDetails.contract.MovieDetailsContract;
import com.mythio.movii.activity.MovieDetails.contract.MovieDetailsContract.Presenter;
import com.mythio.movii.activity.MovieDetails.contract.MovieDetailsPresenter;
import com.mythio.movii.model.movie.Movie;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");
        viewPager = findViewById(R.id.view_pager_details);
        Presenter presenter = new MovieDetailsPresenter(this);

        presenter.getDetails(currentId);
    }

    @Override
    public void showDetails(Movie movie) {

    }
}
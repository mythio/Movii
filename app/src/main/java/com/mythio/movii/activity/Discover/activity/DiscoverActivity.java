package com.mythio.movii.activity.Discover.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.mythio.movii.R;
import com.mythio.movii.activity.Discover.activity.contract.DiscoverContract;
import com.mythio.movii.activity.Discover.activity.contract.DiscoverPresenter;
import com.mythio.movii.activity.Discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.Discover.fragment.MoviesFragment;
import com.mythio.movii.activity.Discover.fragment.ProfileFragment;
import com.mythio.movii.activity.Discover.fragment.TvShowsFragment;
import com.mythio.movii.activity.Discover.fragment.contract.MoviesContract;
import com.mythio.movii.activity.Discover.fragment.contract.TvShowsContract;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements DiscoverContract.View {

    @BindView(R.id.bottom_navigation)
    BubbleNavigationConstraintView navBar;

    private MoviesFragment moviesFragment = new MoviesFragment();
    private TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private static MoviesContract.Callback moviesCallback;
    private static TvShowsContract.Callback tvShowsCallback;

    private DiscoverPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

//        startActivity(new Intent(this, TvShowDetailsActivity.class));
//        finish();

        presenter = new DiscoverPresenter(this);
        presenter.setFragment(moviesFragment);

        presenter.getData();

        moviesCallback = moviesFragment;
        tvShowsCallback = tvShowsFragment;

        navBar.setNavigationChangeListener((view, position) -> {
            switch (position) {
                case 0:
                    presenter.setFragment(moviesFragment);
                    break;
                case 1:
                    presenter.setFragment(tvShowsFragment);
                    break;
                case 2:
                    presenter.setFragment(profileFragment);
                    break;
            }
        });
    }

    @Override
    public void showFragment(BaseDiscoverFragment fragment) {
        fragment.attachPresenter(presenter);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void sendToMoviesFragment(ArrayList<MovieTmdb> movies) {
        moviesCallback.onDataReceived(movies);
    }

    @Override
    public void sendToTvShowsFragment(ArrayList<TvShowTmdb> tvShows) {
        tvShowsCallback.onDataReceived(tvShows);
    }
}
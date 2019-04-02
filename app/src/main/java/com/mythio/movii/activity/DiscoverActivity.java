package com.mythio.movii.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.R;
import com.mythio.movii.contract.activity.discoverActivity.DiscoverContract;
import com.mythio.movii.contract.activity.discoverActivity.DiscoverPresenter;
import com.mythio.movii.contract.fragment.discover.moviesFragment.MoviesContract;
import com.mythio.movii.contract.fragment.discover.tvShowsFragment.TvShowsContract;
import com.mythio.movii.fragment.discover.BaseDiscoverFragment;
import com.mythio.movii.fragment.discover.MoviesFragment;
import com.mythio.movii.fragment.discover.ProfileFragment;
import com.mythio.movii.fragment.discover.TvShowsFragment;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity implements DiscoverContract.View {

//    @BindView(R.id.bottom_navigation)
//    BubbleNavigationConstraintView navBar;

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
//        ButterKnife.bind(this);

        presenter = new DiscoverPresenter(this);
        presenter.setFragment(moviesFragment);

        presenter.onDataRequest();

        BubbleNavigationConstraintView navBar = findViewById(R.id.bottom_navigation);

        moviesCallback = moviesFragment;
        tvShowsCallback = tvShowsFragment;

        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
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
    public void sendToFragmentMovies(ArrayList<MovieTmdb> movies) {
        moviesCallback.onDataReceived(movies);
    }

    @Override
    public void sendToFragmentTvShows(ArrayList<TvShowTmdb> tvShows) {
        tvShowsCallback.onDataReceived(tvShows);
    }
}

package com.mythio.movii.activity.discover.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.mythio.movii.R;
import com.mythio.movii.activity.discover.activity.contract.Contract;
import com.mythio.movii.activity.discover.activity.contract.Presenter;
import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.discover.fragment.MoviesFragment;
import com.mythio.movii.activity.discover.fragment.ProfileFragment;
import com.mythio.movii.activity.discover.fragment.TvShowsFragment;
import com.mythio.movii.activity.discover.fragment.contract.Contract.Callback;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.tvshow.TvShow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.bottom_navigation)
    BubbleNavigationConstraintView navBar;

    private final MoviesFragment moviesFragment = new MoviesFragment();
    private final TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private Fragment activeFragment;

    private static Callback<Movie> moviesCallback;
    private static Callback<TvShow> tvShowsCallback;

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        setPresenter(new Presenter(this));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, moviesFragment, "FRAGMENT 1")
                .add(R.id.fragment_container, tvShowsFragment, "FRAGMENT 2")
                .add(R.id.fragment_container, profileFragment, "FRAGMENT 3")
                .hide(moviesFragment)
                .hide(tvShowsFragment)
                .hide(profileFragment)
                .commit();

        activeFragment = moviesFragment;

        mPresenter.setFragment(moviesFragment);
        mPresenter.getData();

        moviesCallback = moviesFragment;
        tvShowsCallback = tvShowsFragment;

        navBar.setNavigationChangeListener((view, position) -> {
            switch (position) {
                case 0:
                    mPresenter.setFragment(moviesFragment);
                    break;
                case 1:
                    mPresenter.setFragment(tvShowsFragment);
                    break;
                case 2:
                    mPresenter.setFragment(profileFragment);
                    break;
            }
        });
    }

    @Override
    public void showFragment(@NonNull BaseDiscoverFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(activeFragment)
                .show(fragment)
                .commit();

        activeFragment = fragment;
    }

    @Override
    public void sendToMoviesFragment(ArrayList<Movie> movies) {
        moviesCallback.onDataReceived(movies);
    }

    @Override
    public void sendToTvShowsFragment(ArrayList<TvShow> tvShows) {
        tvShowsCallback.onDataReceived(tvShows);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

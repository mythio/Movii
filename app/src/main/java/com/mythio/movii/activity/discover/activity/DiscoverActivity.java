package com.mythio.movii.activity.discover.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.mythio.movii.R;
import com.mythio.movii.activity.discover.activity.contract.Contract;
import com.mythio.movii.activity.discover.activity.contract.Presenter;
import com.mythio.movii.activity.discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.discover.fragment.MoviesFragment;
import com.mythio.movii.activity.discover.fragment.ProfileFragment;
import com.mythio.movii.activity.discover.fragment.TvShowsFragment;
import com.mythio.movii.activity.discover.fragment.contract.Contract.Callback;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.model.tv_show.TvShowTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.bottom_navigation)
    BubbleNavigationConstraintView navBar;

    private final MoviesFragment moviesFragment = new MoviesFragment();
    private final TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();

    private static Callback<MovieTmdb> moviesCallback;
    private static Callback<TvShowTmdb> tvShowsCallback;

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        setPresenter(new Presenter(this));

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

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

package com.mythio.movii.activity.discoverActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.R;
import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.moviesFragment.MoviesContract;
import com.mythio.movii.fragment.moviesFragment.MoviesFragment;
import com.mythio.movii.fragment.profileFragment.ProfileFragment;
import com.mythio.movii.fragment.tvShowsFragment.TvShowsFragment;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.tvShow.TvShow;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity implements DiscoverContract.View {

//    @BindView(R.id.bottom_navigation)
//    BubbleNavigationConstraintView navBar;

    private MoviesFragment moviesFragment = new MoviesFragment();
    private TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private MoviesContract.Callback moviesCallback;

    private DiscoverPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        ButterKnife.bind(this);

        presenter = new DiscoverPresenter(this);
        presenter.setFragment(moviesFragment);

        BubbleNavigationConstraintView navBar = findViewById(R.id.bottom_navigation);

        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        presenter.setFragment(moviesFragment);
                        presenter.onMovieDataRequest();
                        break;
                    case 1:
                        presenter.setFragment(tvShowsFragment);
                        presenter.onTvShowDataRequest();
                        break;
                    case 2:
                        presenter.setFragment(profileFragment);
                        break;
                }
            }
        });

        if (moviesFragment != null) {
            moviesCallback = moviesFragment;
        }
    }

    @Override
    public void showFragment(BaseFragment fragment) {
        fragment.attachPresenter(presenter);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void sendToFragmentMovies(ArrayList<Movie> movies) {
        moviesCallback.onDataReceived(movies);
    }

    @Override
    public void sendToFragmentTvShows(ArrayList<TvShow> tvShows) {
        for (TvShow tvShow : tvShows) {
            Log.d("TAG_TAG_ACTIVITY", tvShow.getName() + tvShow.getRating());
        }
    }
}

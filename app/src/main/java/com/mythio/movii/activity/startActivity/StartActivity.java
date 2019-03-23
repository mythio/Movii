package com.mythio.movii.activity.startActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.R;
import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.moviesFragment.ModelCallback;
import com.mythio.movii.fragment.moviesFragment.MoviesFragment;
import com.mythio.movii.fragment.profileFragment.ProfileFragment;
import com.mythio.movii.fragment.tvShowsFragment.TvShowsFragment;

public class StartActivity extends AppCompatActivity implements Contract.View {

//    @BindView(R.id.bottom_navigation)
//    BubbleNavigationConstraintView navBar;

    private MoviesFragment moviesFragment = new MoviesFragment();
    private TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private ModelCallback modelCallback;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        ButterKnife.bind(this);

        presenter = new Presenter(this);
        presenter.setFragment(moviesFragment);

        BubbleNavigationConstraintView navBar = findViewById(R.id.bottom_navigation);

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

        if (moviesFragment instanceof MoviesFragment) {
            modelCallback = moviesFragment;
        }
        modelCallback.onDataRecieved("Hey!");
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
}

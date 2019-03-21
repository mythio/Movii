package com.mythio.movii.presenter;

import android.support.v4.app.Fragment;

import com.mythio.movii.contract.StartActivity.Contract;
import com.mythio.movii.fragment.MoviesFragment;
import com.mythio.movii.fragment.ProfileFragment;
import com.mythio.movii.fragment.TvShowsFragment;

public class StartActivityPresenter implements Contract.Presenter {

    private Contract.View view;

    public StartActivityPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void initFragment() {
        view.showFragment(new MoviesFragment());
    }

    @Override
    public void setFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MoviesFragment();
                break;
            case 1:
                fragment = new TvShowsFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
                break;
        }

        view.showFragment(fragment);
    }
}
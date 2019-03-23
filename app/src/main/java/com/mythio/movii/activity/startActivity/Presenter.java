package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.MoviesFragment;
import com.mythio.movii.fragment.ProfileFragment;
import com.mythio.movii.fragment.TvShowsFragment;
import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;

public class Presenter implements Contract.Presenter, FragmentNavigation.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void onFragmentSelected(int pos) {
        switch (pos) {
            case 0:
                view.setFragment(new MoviesFragment());
                return;
            case 1:
                view.setFragment(new TvShowsFragment());
                return;
            case 2:
                view.setFragment(new ProfileFragment());
                return;
        }
    }

    @Override
    public void addFragment(BaseFragment fragment) {
        view.setFragment(fragment);
    }
}

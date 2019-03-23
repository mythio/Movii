package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;
import com.mythio.movii.fragment.baseFragment.FragmentNavigation;

public class Presenter implements Contract.Presenter, FragmentNavigation.Presenter {

    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        view.showFragment(fragment);
    }
}

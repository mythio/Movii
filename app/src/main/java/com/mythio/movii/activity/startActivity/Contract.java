package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;

public interface Contract {

    interface View {
        void showFragment(BaseFragment fragment);
    }

    interface Presenter {

    }
}

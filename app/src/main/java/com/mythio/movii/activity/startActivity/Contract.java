package com.mythio.movii.activity.startActivity;

import com.mythio.movii.fragment.baseFragment.BaseFragment;

public interface Contract {

    interface View {
        void setFragment(BaseFragment fragment);
    }

    interface Presenter {
        void onFragmentSelected(int pos);
    }
}

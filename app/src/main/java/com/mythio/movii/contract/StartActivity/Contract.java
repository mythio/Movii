package com.mythio.movii.contract.StartActivity;

import android.support.v4.app.Fragment;

public interface Contract {

    interface View {
        void showFragment(Fragment fragment);
    }

    interface Presenter {
        void initFragment();
        void setFragment(int position);
    }
}

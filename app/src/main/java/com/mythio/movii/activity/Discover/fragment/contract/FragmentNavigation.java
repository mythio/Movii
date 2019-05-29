package com.mythio.movii.activity.Discover.fragment.contract;

import com.mythio.movii.activity.Discover.fragment.BaseDiscoverFragment;

public interface FragmentNavigation {

    interface View {
        void attachPresenter(Presenter presenter);
    }

    interface Presenter {
        void setFragment(BaseDiscoverFragment fragment);
    }
}

package com.mythio.movii.contract.fragment.discover.baseFragmentDiscover;

import com.mythio.movii.fragment.discover.BaseDiscoverFragment;

public interface DiscoverFragmentNavigation {

    interface View {
        void attachPresenter(Presenter presenter);
    }

    interface Presenter {
        void setFragment(BaseDiscoverFragment fragment);
    }
}

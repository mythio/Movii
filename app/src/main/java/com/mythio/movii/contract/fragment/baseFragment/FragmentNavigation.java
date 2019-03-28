package com.mythio.movii.contract.fragment.baseFragment;

import com.mythio.movii.fragment.BaseFragment;

public interface FragmentNavigation {

    interface View {
        void attachPresenter(Presenter presenter);
    }

    interface Presenter {
        void setFragment(BaseFragment fragment);
    }
}

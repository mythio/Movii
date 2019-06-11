package com.mythio.movii.activity.discover.fragment.contract;

import com.mythio.movii.BasePresenter;
import com.mythio.movii.BaseView;

import java.util.ArrayList;

public interface Contract {

    interface View<T> extends BaseView<Presenter<T>> {

        void initViewPager();

        void showSlideShow(ArrayList<T> t);
    }

    interface Presenter<T> extends BasePresenter {

        void initViews();

        void setDataToViewPager(ArrayList<T> t);
    }

    interface Callback<T> {

        void onDataReceived(ArrayList<T> t);
    }
}

package com.mythio.movii.activity.discover.fragment.contract;

import java.util.ArrayList;

public interface Contract {

    interface View<T> {

        void initViewPager();

        void showSlideShow(ArrayList<T> t);
    }

    interface Presenter<T> {

        void initViews();

        void setDataToViewPager(ArrayList<T> t);
    }

    interface Callback<T> {

        void onDataReceived(ArrayList<T> t);
    }
}

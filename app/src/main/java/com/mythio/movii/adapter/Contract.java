package com.mythio.movii.adapter;

public interface Contract {

    interface View<T> {

        void show(T t);
    }

    interface Presenter<T> {

        void onBindViewAtPosition(View<T> t, int position);

        int getCount();
    }
}

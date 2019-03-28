package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.contract.fragment.baseFragment.FragmentNavigation;

public abstract class BaseFragment extends Fragment implements FragmentNavigation.View {

    protected View rootView;
    protected FragmentNavigation.Presenter navigationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    public abstract int getLayout();

    @Override
    public void attachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }
}

package com.mythio.movii.fragment.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.contract.fragment.discover.baseFragmentDiscover.DiscoverFragmentNavigation;

import androidx.fragment.app.Fragment;

public abstract class BaseDiscoverFragment extends Fragment implements DiscoverFragmentNavigation.View {

    protected View rootView;
    protected DiscoverFragmentNavigation.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    protected abstract int getLayout();

    @Override
    public void attachPresenter(DiscoverFragmentNavigation.Presenter presenter) {
        this.presenter = presenter;
    }
}

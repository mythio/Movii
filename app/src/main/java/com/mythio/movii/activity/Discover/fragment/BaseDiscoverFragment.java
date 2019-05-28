package com.mythio.movii.activity.Discover.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mythio.movii.activity.Discover.fragment.contract.DiscoverFragmentNavigation;

import org.jetbrains.annotations.NotNull;

public abstract class BaseDiscoverFragment extends Fragment implements DiscoverFragmentNavigation.View {

    protected DiscoverFragmentNavigation.Presenter presenter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(getLayout(), container, false);
    }

    protected abstract int getLayout();

    @Override
    public void attachPresenter(DiscoverFragmentNavigation.Presenter presenter) {
        this.presenter = presenter;
    }
}

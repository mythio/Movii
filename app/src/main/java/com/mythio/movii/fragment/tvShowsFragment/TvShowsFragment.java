package com.mythio.movii.fragment.tvShowsFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mythio.movii.R;
import com.mythio.movii.fragment.baseFragment.BaseFragment;

public class TvShowsFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tv_shows;
    }
}
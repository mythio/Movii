package com.mythio.movii.fragment.moviesFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.mythio.movii.R;
import com.mythio.movii.fragment.baseFragment.BaseFragment;

public class MoviesFragment extends BaseFragment implements ModelCallback {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movies;
    }

    @Override
    public void onDataRecieved(String string) {
        Log.v("TAG_TAG", string);
    }
}

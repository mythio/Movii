package com.mythio.movii.fragment.discover;

import android.os.Bundle;
import android.view.View;

import com.mythio.movii.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileFragment extends BaseDiscoverFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_profile;
    }
}

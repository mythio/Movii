package com.mythio.movii.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.R;
import com.mythio.movii.fragment.MoviesFragment;
import com.mythio.movii.fragment.ProfileFragment;
import com.mythio.movii.fragment.TvShowsFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BubbleNavigationConstraintView navBar = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesFragment()).commit();

        navBar.setSelected(true);

        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new MoviesFragment();
                        break;
                    case 1:
                        fragment = new TvShowsFragment();
                        break;
                    case 2:
                        fragment = new ProfileFragment();
                        break;
                }
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    ft.replace(R.id.fragment_container, fragment).commit();
                }
            }
        });
    }
}
package com.mythio.movii.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.contract.StartActivity.Contract;
import com.mythio.movii.presenter.StartActivityPresenter;
import com.mythio.movii.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.bottom_navigation)
    BubbleNavigationConstraintView navBar;

    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

        presenter = new StartActivityPresenter(this);
        presenter.initFragment();

        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                presenter.setFragment(position);
            }
        });
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.replace(R.id.fragment_container, fragment).commit();
    }
}
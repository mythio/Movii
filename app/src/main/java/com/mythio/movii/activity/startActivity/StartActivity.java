package com.mythio.movii.activity.startActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mythio.movii.R;
import com.mythio.movii.fragment.MoviesFragment;
import com.mythio.movii.fragment.baseFragment.BaseFragment;

public class StartActivity extends AppCompatActivity implements Contract.View {

//    @BindView(R.id.bottom_navigation)
//    BubbleNavigationConstraintView navBar;

    private BubbleNavigationConstraintView navBar;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        ButterKnife.bind(this);

        presenter = new Presenter(this);
        setFragment(new MoviesFragment());

        navBar = findViewById(R.id.bottom_navigation);

        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                presenter.onFragmentSelected(position);
            }
        });
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        fragment.attachPresenter(presenter);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}

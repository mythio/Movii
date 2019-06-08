package com.mythio.movii.activity.tv_show_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.tv_show_details.contract.Contract;
import com.mythio.movii.activity.tv_show_details.contract.Presenter;
import com.mythio.movii.adapter.view_pager_adapter.TvShowDetailsAdapter;
import com.mythio.movii.model.tv_show.TvShow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.vp_details)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        ButterKnife.bind(this);

        Contract.Presenter presenter = new Presenter(this);

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_TV_ID");
        presenter.onRequestSeasons(currentId);
    }

    @Override
    public void showDetails(TvShow tvShow) {
        viewPager.setAdapter(new TvShowDetailsAdapter(this, tvShow));
    }
}

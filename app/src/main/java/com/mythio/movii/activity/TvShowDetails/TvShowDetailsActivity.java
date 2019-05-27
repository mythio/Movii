package com.mythio.movii.activity.TvShowDetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.TvShowDetails.contract.TvShowDetailsContract;
import com.mythio.movii.activity.TvShowDetails.contract.TvShowDetailsPresenter;
import com.mythio.movii.adapter.viewPagerAdapter.TvShowDetailsAdapter;
import com.mythio.movii.model.tvShow.TvShow;

public class TvShowDetailsActivity extends AppCompatActivity implements TvShowDetailsContract.View {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);

        TvShowDetailsContract.Presenter presenter = new TvShowDetailsPresenter(this);

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_TV_ID");
        presenter.onRequestSeasons(currentId);

        viewPager = findViewById(R.id.view_pager_details);
    }

    @Override
    public void showDetails(TvShow tvShow) {
        viewPager.setAdapter(new TvShowDetailsAdapter(this, tvShow));
    }
}

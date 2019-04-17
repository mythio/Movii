package com.mythio.movii.activity;

import android.os.Bundle;
import android.util.Log;

import com.mythio.movii.R;
import com.mythio.movii.contract.activity.tvShowDetailsActivity.TvShowDetailsContract;
import com.mythio.movii.contract.activity.tvShowDetailsActivity.TvShowDetailsPresenter;
import com.mythio.movii.model.season.SeasonDetails;
import com.mythio.movii.model.tvShow.TvShow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TvShowDetailsActivity extends AppCompatActivity implements TvShowDetailsContract.View {

    String currentId;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);

//        currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");

        TvShowDetailsContract.Presenter presenter = new TvShowDetailsPresenter(this);
        presenter.onGetDetails(1399);

//        viewPager = findViewById(R.id.view_pager_details);
    }

    @Override
    public void showDetails(TvShow tvShow) {
        for (SeasonDetails details : tvShow.getSeasons()) {
            Log.d("TAG_TAG", details.getName() + " " + details.getSeasonNumber());
        }
    }
}

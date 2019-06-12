package com.mythio.movii.activity.tv_show_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mythio.movii.R;
import com.mythio.movii.activity.tv_show_details.contract.Contract;
import com.mythio.movii.activity.tv_show_details.contract.Presenter;
import com.mythio.movii.model.tv_show.TvShow;

import butterknife.ButterKnife;

public class TvShowDetailsActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        ButterKnife.bind(this);
        setPresenter(new Presenter(this));

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_TV_ID");
        mPresenter.onRequestSeasons(currentId);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showDetails(TvShow tvShow) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

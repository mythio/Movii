package com.mythio.movii.activity.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.discover.fragment.contract.Contract;
import com.mythio.movii.activity.discover.fragment.contract.Presenter;
import com.mythio.movii.activity.search_tv_show.SearchTvShowActivity;
import com.mythio.movii.activity.tv_show_details.TvShowDetailsActivity;
import com.mythio.movii.adapter.view_pager_adapter.TvShowSliderAdapter;
import com.mythio.movii.model.tv_show.TvShow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowsFragment extends BaseDiscoverFragment implements Contract.View<TvShow>,
        Contract.Callback<TvShow> {

    @BindView(R.id.vp_popular)
    ViewPager viewPager;

    private Contract.Presenter<TvShow> mPresenter;
    private ArrayList<TvShow> tvShows;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setPresenter(new Presenter<>(this));

        mPresenter.initViews();
    }

    @OnClick(R.id.search_btn_go)
    void send() {
        startActivity(new Intent(getContext(), SearchTvShowActivity.class));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tv_shows;
    }

    @Override
    public void onDataReceived(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
        mPresenter.setDataToViewPager(tvShows);
    }

    @Override
    public void initViewPager() {
        viewPager.setPageTransformer(false, (view, v) -> {
            view.findViewById(R.id.iv_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
            view.findViewById(R.id.tv_title1).setAlpha(1.0F - Math.abs(v) * 2);
            view.findViewById(R.id.tv_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
        });
    }

    @Override
    public void showSlideShow(ArrayList<TvShow> tvShows) {
        TvShowSliderAdapter adapter = new TvShowSliderAdapter(getContext(), tvShows, position -> {
            Intent intent = new Intent(getContext(), TvShowDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_TV_ID", tvShows.get(position).getId());
            startActivity(intent);
        });
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setPresenter(Contract.Presenter<TvShow> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

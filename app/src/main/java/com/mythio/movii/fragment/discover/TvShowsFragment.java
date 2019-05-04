package com.mythio.movii.fragment.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mythio.movii.R;
import com.mythio.movii.activity.SearchTvShowActivity;
import com.mythio.movii.adapter.viewPagerAdapter.TvShowSliderAdapter;
import com.mythio.movii.contract.fragment.discover.baseFragmentDiscover.OnItemClickListener;
import com.mythio.movii.contract.fragment.discover.tvShowsFragment.TvShowsContract;
import com.mythio.movii.contract.fragment.discover.tvShowsFragment.TvShowsPresenter;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowsFragment extends BaseDiscoverFragment implements TvShowsContract.View,
        TvShowsContract.Callback, OnItemClickListener {

    @BindView(R.id.view_pager_popular)
    ViewPager viewPager;

    private TvShowsContract.Presenter mPresenter;
    private ArrayList<TvShowTmdb> tvShows;
    private Boolean hasReceived = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new TvShowsPresenter(this);
        mPresenter.initViews();

        if (hasReceived) {
            mPresenter.setDataToViewPager(tvShows);
        }
    }

    @OnClick(R.id.search_btn_go)
    public void send() {

        startActivity(new Intent(getContext(), SearchTvShowActivity.class));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tv_shows;
    }

    @Override
    public void onDataReceived(ArrayList<TvShowTmdb> tvShows) {
        hasReceived = true;
        this.tvShows = tvShows;

        if (mPresenter != null) {
            mPresenter.setDataToViewPager(tvShows);
        }
    }

    @Override
    public void initViewPager() {

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.findViewById(R.id.image_view_backdrop).setTranslationX(-v * viewPager.getWidth() / 4);
                view.findViewById(R.id.text_view_title1).setAlpha(1.0F - Math.abs(v) * 2);
                view.findViewById(R.id.text_view_title2).setAlpha(0.6F * (1.0F - Math.abs(v) * 2));
            }
        });
    }

    @Override
    public void showSlideShow(ArrayList<TvShowTmdb> tvShows) {

        TvShowSliderAdapter adapter = new TvShowSliderAdapter(getContext(), tvShows);
        adapter.setOnClickListener(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

    }
}

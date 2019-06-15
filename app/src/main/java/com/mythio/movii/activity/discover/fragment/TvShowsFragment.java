package com.mythio.movii.activity.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.mythio.movii.R;
import com.mythio.movii.activity.discover.fragment.contract.Contract;
import com.mythio.movii.activity.discover.fragment.contract.Presenter;
import com.mythio.movii.activity.search_tv_show.SearchTvShowActivity;
import com.mythio.movii.adapter.popular_tvshow.PopularTvshowAdapter;
import com.mythio.movii.adapter.popular_tvshow.PopularTvshowPresenter;
import com.mythio.movii.model.tv_show.TvShow;
import com.mythio.movii.util.CarouselLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TvShowsFragment extends BaseDiscoverFragment implements Contract.View<TvShow>,
        Contract.Callback<TvShow> {

    @BindView(R.id.vp_popular)
    RecyclerView rvSlideShow;

    private Contract.Presenter<TvShow> mPresenter;
    private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setPresenter(new Presenter<>(this));

        rvSlideShow = view.findViewById(R.id.vp_popular);
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
        mPresenter.setDataToViewPager(tvShows);
    }

    @Override
    public void showSlideShow(ArrayList<TvShow> tvShows) {
        rvSlideShow.setLayoutManager(new CarouselLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false,
                0.05f, 0.2f));
        rvSlideShow.setAdapter(new PopularTvshowAdapter(new PopularTvshowPresenter(tvShows), null));

        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(rvSlideShow);
    }

    @Override
    public void setPresenter(Contract.Presenter<TvShow> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        unbinder.unbind();
    }
}

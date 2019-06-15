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
import com.mythio.movii.activity.movie_details.MovieDetailsActivity;
import com.mythio.movii.activity.search_movie.SearchMovieActivity;
import com.mythio.movii.adapter.popular_movies.PopularMovieAdapter;
import com.mythio.movii.adapter.popular_movies.PopularMoviePresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.CarouselLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MoviesFragment extends BaseDiscoverFragment implements Contract.View<Movie>,
        Contract.Callback<Movie> {

    @BindView(R.id.vp_popular)
    RecyclerView rvSlideShow;

    private Contract.Presenter<Movie> mPresenter;
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
        startActivity(new Intent(getContext(), SearchMovieActivity.class));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movies;
    }

    @Override
    public void onDataReceived(ArrayList<Movie> movies) {
        mPresenter.setDataToViewPager(movies);
    }

    @Override
    public void showSlideShow(ArrayList<Movie> movies) {
        rvSlideShow.setLayoutManager(new CarouselLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false,
                0.05f, 0.2f));
        rvSlideShow.setAdapter(new PopularMovieAdapter(new PopularMoviePresenter(movies), position -> {
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", movies.get(position).getId());
            startActivity(intent);
        }));

        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(rvSlideShow);
    }

    @Override
    public void setPresenter(Contract.Presenter<Movie> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        unbinder.unbind();
    }
}

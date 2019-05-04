package com.mythio.movii.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.adapter.recyclerViewAdapter.MovieSearchAdapter;
import com.mythio.movii.adapter.recyclerViewAdapter.TvShowSearchAdapter;
import com.mythio.movii.contract.activity.searchTvShowActivity.SearchTvShowContract;
import com.mythio.movii.contract.activity.searchTvShowActivity.SearchTvShowPresenter;
import com.mythio.movii.model.tvShow.TvShowTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvShowActivity extends AppCompatActivity implements SearchTvShowContract.View {

    @BindView(R.id.search_bar)
    EditText searchBar;

    @BindView(R.id.search_plate)
    LinearLayout searchPlate;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchTvShowContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        ButterKnife.bind(this);
        presenter = new SearchTvShowPresenter(this);

        MovieSearchAdapter.ItemDecorator decorator = new MovieSearchAdapter.ItemDecorator(12);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.addItemDecoration(decorator);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().isEmpty()) {
                    presenter.onNoSearchParam();
                } else {
                    presenter.onSearchParam(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void showPlate() {

        if (searchPlate.getVisibility() == View.INVISIBLE) {

            searchPlate.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_in));
            recyclerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_out));
            searchPlate.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void hidePlate() {

        if (searchPlate.getVisibility() == View.VISIBLE) {

            searchPlate.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_out));
            recyclerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_in));
            searchPlate.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showRes(ArrayList<TvShowTmdb> tvShows) {

        TvShowSearchAdapter adapter = new TvShowSearchAdapter(this, tvShows);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.setAdapter(adapter);
    }
}
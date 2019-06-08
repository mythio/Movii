package com.mythio.movii.activity.search_tv_show;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.search_tv_show.contract.Contract;
import com.mythio.movii.activity.search_tv_show.contract.Presenter;
import com.mythio.movii.activity.tv_show_details.TvShowDetailsActivity;
import com.mythio.movii.adapter.recycler_view_adapter.search_tv_show.SearchTvShowAdapter;
import com.mythio.movii.adapter.recycler_view_adapter.search_tv_show.SearchTvShowPresenter;
import com.mythio.movii.model.tv_show.TvShowTmdb;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvShowActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.search_bar)
    EditText searchBar;

    @BindView(R.id.search_plate)
    LinearLayout searchPlate;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv_show);

        ButterKnife.bind(this);
        presenter = new Presenter(this);

        ItemDecorator decorator = new ItemDecorator(12, ItemDecorator.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.addItemDecoration(decorator);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
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
            recyclerView.setAdapter(null);
        }
    }

    @Override
    public void showSearchResult(ArrayList<TvShowTmdb> tvShows) {
        SearchTvShowPresenter presenter = new SearchTvShowPresenter(tvShows);
        SearchTvShowAdapter adapter = new SearchTvShowAdapter(presenter, position -> {
            Intent intent = new Intent(SearchTvShowActivity.this, TvShowDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_TV_ID", tvShows.get(position).getId());
            startActivity(intent);
        });
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.setAdapter(adapter);
    }
}
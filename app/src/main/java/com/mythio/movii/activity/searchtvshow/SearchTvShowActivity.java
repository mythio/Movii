package com.mythio.movii.activity.searchtvshow;

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
import com.mythio.movii.activity.searchtvshow.contract.Contract;
import com.mythio.movii.activity.searchtvshow.contract.Presenter;
import com.mythio.movii.activity.tvshowdetails.TvShowDetailsActivity;
import com.mythio.movii.adapter.searchtvshow.SearchTvShowAdapter;
import com.mythio.movii.adapter.searchtvshow.SearchTvShowPresenter;
import com.mythio.movii.model.tvshow.TvShow;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTvShowActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.search_bar)
    EditText searchBar;

    @BindView(R.id.search_plate)
    LinearLayout searchPlate;

    @BindView(R.id.rv_search_result)
    RecyclerView recyclerView;

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv_show);
        ButterKnife.bind(this);
        setPresenter(new Presenter(this));

        ItemDecorator decorator = new ItemDecorator(12, ItemDecorator.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.addItemDecoration(decorator);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*
                Not used
                 */
            }

            @Override
            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    mPresenter.onNoSearchParam();
                } else {
                    mPresenter.onSearchParam(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*
                Not used
                 */
            }
        });
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
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
    public void showSearchResult(ArrayList<TvShow> tvShows) {
        SearchTvShowPresenter presenter = new SearchTvShowPresenter(tvShows);
        SearchTvShowAdapter adapter = new SearchTvShowAdapter(presenter, position -> {
            Intent intent = new Intent(SearchTvShowActivity.this, TvShowDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_TV_ID", tvShows.get(position).getId());
            startActivity(intent);
        });
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
package com.mythio.movii.activity.searchmovie;

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
import com.mythio.movii.activity.moviedetails.MovieDetailsActivity;
import com.mythio.movii.activity.searchmovie.contract.Contract;
import com.mythio.movii.activity.searchmovie.contract.Presenter;
import com.mythio.movii.adapter.searchmovie.SearchMovieAdapter;
import com.mythio.movii.adapter.searchmovie.SearchMoviePresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieActivity extends AppCompatActivity implements Contract.View {

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
        setContentView(R.layout.activity_search_movie);
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
        this.mPresenter = presenter;
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
    public void showSearchResult(@NonNull ArrayList<Movie> movies) {
        SearchMoviePresenter presenter = new SearchMoviePresenter(movies);
        SearchMovieAdapter adapter = new SearchMovieAdapter(presenter, position -> {
            Intent intent = new Intent(SearchMovieActivity.this, MovieDetailsActivity.class);
            intent.putExtra("BUNDLED_EXTRA_MOVIE_ID", movies.get(position).getId());
            startActivity(intent);
        });
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }
}
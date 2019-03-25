package com.mythio.movii.activity.searchMovieActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mythio.movii.R;
import com.mythio.movii.adapter.MovieSearchAdapter;
import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieContract.View {

    @BindView(R.id.search_bar)
    EditText searchBar;

    @BindView(R.id.search_plate_image)
    ImageView searchPlate;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        ButterKnife.bind(this);
        presenter = new SearchMoviePresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().equals("")) {
                    presenter.onNoSearchParam();
                } else {
                    presenter.onSearchParam(s.toString());
                }
            }
        });
    }

    @Override
    public void showPlate() {
        searchPlate.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRes(ArrayList<MovieTmdb> movieTmdbArrayList) {
        MovieSearchAdapter adapter = new MovieSearchAdapter(this, movieTmdbArrayList);
        recyclerView.setAdapter(adapter);
    }
}
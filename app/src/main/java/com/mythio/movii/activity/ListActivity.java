package com.mythio.movii.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mMovies = new ArrayList<>();

        Intent i = getIntent();
        mMovies = (ArrayList<Movie>) i.getSerializableExtra("POPULAR_MOVIES_LIST");
    }
}

package com.mythio.movii.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mythio.movii.R;
import com.mythio.movii.model.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

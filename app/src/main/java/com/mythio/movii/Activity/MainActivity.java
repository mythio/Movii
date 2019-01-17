package com.mythio.movii.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.mythio.movii.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mImageView = findViewById(R.id.image_view_poster_bg);

        RatingBar ratingBar = findViewById(R.id.rating_bar_stars);

        ratingBar.setRating((float) 4.5);

        String url = "https://image.tmdb.org/t/p/original";
        url += "/AkJQpZp9WoNdj7pLYSj1L0RcMMN.jpg";
        Picasso.get().load(url).placeholder(R.color.colorAccent).resize(2000, 3000).centerInside().into(mImageView);
    }
}

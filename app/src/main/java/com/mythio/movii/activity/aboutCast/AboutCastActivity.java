package com.mythio.movii.activity.aboutCast;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mythio.movii.R;
import com.mythio.movii.util.App;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class AboutCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cast);

        ImageView imageView = findViewById(R.id.cast);

        String url = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");

        Glide.with(App.getContext())
                .load(IMAGE_BASE_URL + "w185" + url)
                .into(imageView);
    }
}

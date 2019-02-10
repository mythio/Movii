package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mythio.movii.R;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Movie;
import com.mythio.movii.model.Person;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class CastBottomSheetDialog extends BottomSheetDialogFragment {

    private ArrayList<Movie> mMovies;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;

    private ImageView mImageViewProfile;
    private TextView mTextViewName;

    private Person person;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        person = (Person) Objects.requireNonNull(getArguments()).getSerializable("message");

        View view = inflater.inflate(R.layout.bottom_sheet_cast, container, false);

        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        mImageViewProfile = view.findViewById(R.id.image_view_profile);
        mTextViewName = view.findViewById(R.id.text_view_name);

        String url = TMDB_IMAGE + "w185" + person.getProfile_path();

        Picasso.get().load(url)
                .placeholder(R.drawable.movie_placeholder)
                .into(mImageViewProfile);
        mTextViewName.setText(person.getName());

//        parse();
        return view;
    }

    private void parse() {
        String url = Constants.TMDB_PERSON + person.getId() + "?api_key=" + TMDB_API_KEY;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String purl = response.getString("profile_path");
                        String name = response.getString("name");

                        purl = TMDB_IMAGE + "w185" + purl;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
        });
        mRequestQueue.add(jsonObjectRequest);
    }
}
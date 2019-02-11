package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.mythio.movii.adapter.FeaturedMovieAdapter;
import com.mythio.movii.constant.Constants;
import com.mythio.movii.model.Movie;
import com.mythio.movii.model.Person;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.GENRE;
import static com.mythio.movii.constant.Constants.TMDB_API_KEY;
import static com.mythio.movii.constant.Constants.TMDB_IMAGE;

public class CastBottomSheetDialog extends BottomSheetDialogFragment {

    private ArrayList<Movie> mMovies;
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;

    private Person person;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mMovies = new ArrayList<>();

        person = (Person) Objects.requireNonNull(getArguments()).getSerializable("message");

        View view = inflater.inflate(R.layout.bottom_sheet_cast, container, false);

        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        ImageView mImageViewProfile = view.findViewById(R.id.image_view_profile);
        TextView mTextViewName = view.findViewById(R.id.text_view_name);

        String url = TMDB_IMAGE + "w185" + person.getProfile_path();

        Picasso.get().load(url)
                .placeholder(R.drawable.movie_placeholder)
                .into(mImageViewProfile);
        mTextViewName.setText(person.getName());

        recyclerView = view.findViewById(R.id.recycler_view_cast_featured_movies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        parse();
        return view;
    }

    private void parse() {
        String url = Constants.TMDB_PERSON + person.getId() + "/movie_credits?api_key=" + TMDB_API_KEY;

        Log.d("tag_tag_tag", url);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("cast");
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            Log.d("TAG_TAG_TAG", jsonArray.getJSONObject(i).toString());
                            addToList(jsonArray.getJSONObject(i));
                        }

                        FeaturedMovieAdapter adapter = new FeaturedMovieAdapter(getContext(), mMovies);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private void addToList(JSONObject jsonObject) throws JSONException {

        String character = jsonObject.getString("character");

        if (character.equals("Himself")) {
            return;
        }

        String title = jsonObject.getString("title");
        String poster_path = jsonObject.getString("poster_path");
        String id = String.valueOf(jsonObject.getInt("id"));
        JSONArray genreArr = jsonObject.getJSONArray("genre_ids");

        StringBuilder genre = null;
        String[] title_arr = title.split(": ");
        String title1;
        String title2 = "";

        if (title_arr.length == 2) {
            title1 = title_arr[0].trim();
            title2 = title_arr[1].trim();
        } else {
            title1 = title_arr[0].trim();
        }

        int l = Math.min(genreArr.length(), 2);

        for (int j = 0; j < l; ++j) {
            genre = (genre == null ? new StringBuilder() : genre).append(GENRE.get(genreArr.getInt(j)));
            if (j != l - 1) {
                genre.append("  |  ");
            }
        }

        mMovies.add(new Movie(
                poster_path,
                title1,
                title2,
                id,
                genre == null ? null : genre.toString(),
                ""
        ));
    }
}
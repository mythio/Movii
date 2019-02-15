package com.mythio.movii.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mythio.movii.R;
import com.mythio.movii.adapter.SliderAdapter;
import com.mythio.movii.fragment.movies.MoviesFragmentGenre;
import com.mythio.movii.fragment.movies.MoviesFragmentLatest;
import com.mythio.movii.fragment.movies.MoviesFragmentPopular;
import com.mythio.movii.fragment.movies.MoviesFragmentTopRated;
import com.mythio.movii.fragment.movies.MoviesFragmentTrending;
import com.mythio.movii.fragment.movies.MoviesFragmentUpcoming;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MoviesFragment extends Fragment {

    private ViewPager viewPager;
    List<String> url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_temp, container, false);
        url  = new ArrayList<>();
        url.add("https://image.tmdb.org/t/p/w780/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg");
        url.add("https://image.tmdb.org/t/p/w780/8yqLPNwNCtpOPc3XkOlkSMnghzw.jpg");
        url.add("https://image.tmdb.org/t/p/w780/lvjscO8wmpEbIfOEZi92Je8Ktlg.jpg");
        url.add("https://image.tmdb.org/t/p/w780/8yqLPNwNCtpOPc3XkOlkSMnghzw.jpg");
        viewPager = view.findViewById(R.id.view_pager);
//        indicator = findViewById(R.id.indicator);
        viewPager.setAdapter(new SliderAdapter(getContext(), url));
//        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000,  4000);
        return view;
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                if (viewPager.getCurrentItem() < url.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            });
        }
    }
}

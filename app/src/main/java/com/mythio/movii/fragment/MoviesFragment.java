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
import com.mythio.movii.fragment.movies.MoviesFragmentGenre;
import com.mythio.movii.fragment.movies.MoviesFragmentLatest;
import com.mythio.movii.fragment.movies.MoviesFragmentPopular;
import com.mythio.movii.fragment.movies.MoviesFragmentTopRated;
import com.mythio.movii.fragment.movies.MoviesFragmentTrending;
import com.mythio.movii.fragment.movies.MoviesFragmentUpcoming;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new MoviesFragmentPopular(), "Popular");
        adapter.addFragment(new MoviesFragmentUpcoming(), "Upcoming");
        adapter.addFragment(new MoviesFragmentLatest(), "Latest");
        adapter.addFragment(new MoviesFragmentTrending(), "Trending");
        adapter.addFragment(new MoviesFragmentTopRated(), "Top Rated");
        adapter.addFragment(new MoviesFragmentGenre(), "Genre");
        viewPager.setAdapter(adapter);
        viewPager.stopNestedScroll();
        viewPager.setOffscreenPageLimit(3);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

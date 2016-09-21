package com.example.inspired.inspiredvideo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inspired.inspiredvideo.view.fragments.MoviesListFragment;

/**
 * Created by vdimitrova on 21.09.16.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // MoviesListFragment is our TabFragment.
        return MoviesListFragment.newInstance("");
    }

    @Override
    public int getCount() {
        return 3;
    }
}

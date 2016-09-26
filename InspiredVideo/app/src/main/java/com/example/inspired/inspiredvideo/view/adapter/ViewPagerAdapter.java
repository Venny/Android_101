package com.example.inspired.inspiredvideo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.view.fragments.MoviesListFragment;

/**
 * Created by vdimitrova on 21.09.16.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("New item");
        // MoviesListFragment is our TabFragment.
        switch (position) {
            case 0:
            case 1:
            case 2:
                return MoviesListFragment.newInstance("");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

package com.example.inspired.inspiredvideo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inspired.inspiredvideo.R;
import com.example.inspired.inspiredvideo.view.fragments.MoviesListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdimitrova on 21.09.16.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    private int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager manager, int numOfTabs) {
        super(manager);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

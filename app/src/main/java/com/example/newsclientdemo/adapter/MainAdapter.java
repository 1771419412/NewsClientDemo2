package com.example.newsclientdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 雪无痕 on 2017/5/27.
 */

public class MainAdapter extends FragmentPagerAdapter{

    private List<Fragment> mainFragmentList;

    public MainAdapter(FragmentManager fm,List<Fragment> mainFragmentList) {
        super(fm);
        this.mainFragmentList=mainFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mainFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mainFragmentList.size();
    }
}

package com.example.newsclientdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newsclientdemo.fragment.NewsFragment;

import java.util.List;

/**
 * Created by 雪无痕 on 2017/5/16.
 */

public class ContentFragmentAdapter extends FragmentPagerAdapter {

    private List<NewsFragment> mFragmentList;
    private List<String> mTitleList;


    public ContentFragmentAdapter(FragmentManager fm, List<NewsFragment> contentFragmentList, List<String> titleList) {
        super(fm);
        this.mFragmentList = contentFragmentList;
        this.mTitleList = titleList;

    }


    @Override
    public Fragment getItem(int position) {
        if(position < mFragmentList.size()){
            return mFragmentList.get(position);
        }
        return null;


    }

    @Override
    public int getCount() {
        if(mFragmentList == null){
            return 0;
        }
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null && position < mTitleList.size()) {
            return mTitleList.get(position);
        }
        return "no";
    }
}

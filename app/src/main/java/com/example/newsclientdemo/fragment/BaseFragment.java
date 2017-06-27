package com.example.newsclientdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.ContentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 雪无痕 on 2017/5/24.
 */

public class BaseFragment extends Fragment{
    private TabLayout mTabFragmentTitle;
    private ViewPager mVpFragmentPager;

    private List<String> mTitleList = new ArrayList<String>();
    private ContentFragmentAdapter mContentFragmentAdapter;

    private List<NewsFragment> mContentFragmentList = new ArrayList<NewsFragment>();
    private TextView mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmemt_base,container,false);
        initFragmentArrayList();
        initTitleList();
        initTabLayout(view);
        mTitle= (TextView) view.findViewById(R.id.title_tv);
        mTitle.setText("新闻专场");
        return view;
    }



    private void initTabLayout(View view) {
        mTabFragmentTitle = (TabLayout)view.findViewById(R.id.tab_FindFragment_title);
        mVpFragmentPager = (ViewPager)view.findViewById(R.id.vp_FindFragment_pager);

        mContentFragmentAdapter = new ContentFragmentAdapter(getChildFragmentManager(), mContentFragmentList, mTitleList);
        mVpFragmentPager.setAdapter(mContentFragmentAdapter);
        mVpFragmentPager.setOffscreenPageLimit(8     );
        mTabFragmentTitle.setupWithViewPager(mVpFragmentPager);
    }

    private void initTitleList() {
        mTitleList.add("头条");
        mTitleList.add("军事");
        mTitleList.add("体育");
        mTitleList.add("娱乐");
        mTitleList.add("科技");
        mTitleList.add("社会");
        mTitleList.add("国内");
        mTitleList.add("国际");

    }

    private void initFragmentArrayList() {
        NewsFragment tt = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("NEWSTYPE", 1);
        tt.setArguments(bundle);

        NewsFragment js = new NewsFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("NEWSTYPE", 2);
        js.setArguments(bundle2);

        NewsFragment ty = new NewsFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("NEWSTYPE", 3);
        ty.setArguments(bundle3);

        NewsFragment yl = new NewsFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("NEWSTYPE", 4);
        yl.setArguments(bundle4);

        NewsFragment kj = new NewsFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putInt("NEWSTYPE", 5);
        kj.setArguments(bundle5);

        NewsFragment sh = new NewsFragment();
        Bundle bundle6 = new Bundle();
        bundle6.putInt("NEWSTYPE", 6);
        sh.setArguments(bundle6);

        NewsFragment gn = new NewsFragment();
        Bundle bundle7 = new Bundle();
        bundle7.putInt("NEWSTYPE", 7);
        gn.setArguments(bundle7);

        NewsFragment gj = new NewsFragment();
        Bundle bundle8 = new Bundle();
        bundle8.putInt("NEWSTYPE", 8);
        gj.setArguments(bundle);


        mContentFragmentList.add(tt);
        mContentFragmentList.add(js);
        mContentFragmentList.add(ty);
        mContentFragmentList.add(yl);
        mContentFragmentList.add(kj);
        mContentFragmentList.add(sh);
        mContentFragmentList.add(gn);
        mContentFragmentList.add(gj);


    }

}

package com.example.newsclientdemo.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.ContentFragmentAdapter;
import com.example.newsclientdemo.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity{

    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;
    private TextView mTitle;


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;


    private TabLayout mTabFragmentTitle;
    private ViewPager mVpFragmentPager;

    private List<String> mTitleList = new ArrayList<String>();
    private ContentFragmentAdapter mContentFragmentAdapter;

    private List<NewsFragment> mContentFragmentList = new ArrayList<NewsFragment>();

    private List<Fragment> mFragmentList;
    private int mPosition;
    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtonViews();
        mTvHome.setSelected(true);
        initFragmentArrayList();
        initTitleList();
        initTabLayout();
        initNavigationView();
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("新闻专场");


    }




    private void initNavigationView() {
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drlayout);
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_item_home:


                        break;

                    case R.id.navigation_item_blog:


                        break;

                    case R.id.navigation_item_about:


                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;



            }
        });
    }


    private void initButtonViews() {
        mTvHome = (LinearLayout) findViewById(R.id.tv_home);
        mTvBBS = (LinearLayout) findViewById(R.id.tv_bbs);
        mTvPaly = (LinearLayout) findViewById(R.id.tv_play);
        mTvMy = (LinearLayout) findViewById(R.id.tv_my);


        mTvHome.setOnClickListener(this);
        mTvBBS.setOnClickListener(this);
        mTvMy.setOnClickListener(this);
        mTvPaly.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_home:
                goTo(MainActivity.class);
                finish();

                break;

            case R.id.tv_bbs:
                goTo(ReadActivity.class);
                finish();

                break;


            case R.id.tv_play:
                goTo(VideoActivity.class);
                finish();

                break;
            case R.id.tv_my:
                goTo(MyActivity.class);
                finish();

                break;

        }


    }

    private void initTabLayout() {
        mTabFragmentTitle = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        mVpFragmentPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);

        mContentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(), mContentFragmentList, mTitleList);
        mVpFragmentPager.setAdapter(mContentFragmentAdapter);
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

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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.ContentFragmentAdapter;
import com.example.newsclientdemo.fragment.BaseFragment;
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

    private FrameLayout mFrameLayout;


    private TabLayout mTabFragmentTitle;
    private ViewPager mVpFragmentPager;

    private List<String> mTitleList = new ArrayList<String>();
    private ContentFragmentAdapter mContentFragmentAdapter;

    private List<NewsFragment> mContentFragmentList = new ArrayList<NewsFragment>();

    private List<Fragment> mFragmentList;
    private int mPosition;
    private Fragment mFragment;
    private BaseFragment mBf;


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
        /*initFragment();*/
       /* setFragment();*/
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("新闻专场");


    }

    /*private void setFragment() {
        mPosition=0;
        //根据位置得到对应的fragment
        Fragment toFragment = getFragment();
        //切换fragment
        //  switchFragment(toFragment); 会导致每次切换fragment切换都重新初始化
        switchContent(mFragment,toFragment);
    }*/

    /*private void initFragment() {
        mFragmentList=new ArrayList<>();
        mBf = new BaseFragment();
        MyFragment myFragment=new MyFragment();
        mFragmentList.add(mBf);
        mFragmentList.add(myFragment);

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.framelayout_content, mBf);
    }*/

    //根据位置得到对应的fragment
  /* private Fragment getFragment() {
        Fragment fragment = mFragmentList.get(mPosition);
        return fragment;
    }*/


    /*public void switchContent(Fragment fromFragment, Fragment toFragment) {
        if(fromFragment != toFragment){
            mFragment = toFragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!toFragment.isAdded()){
                //toFragment没有被添加
                //1.先隐藏fromFragment
                if(fromFragment != null){
                    ft.hide(fromFragment);
                }
                //2.再添加toFragment
                if(toFragment != null){
                    ft.add(R.id.framelayout_content,toFragment).commit();
                }
            }else{
                //toFragment已被添加
                //1.先隐藏fromFragment
                if(fromFragment != null){
                    ft.hide(fromFragment);
                }
                //2.再显示toFragment
                if(toFragment != null){
                    ft.show(toFragment).commit();
                }
            }
        }
    }*/



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

                mTvHome.setSelected(true);
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
        //根据位置得到对应的fragment
        //Fragment toFragment = getFragment();
        //切换fragment
        //  switchFragment(toFragment); 会导致每次切换fragment切换都重新初始化
        //switchContent(mFragment,toFragment);


    }

    private void initTabLayout() {
        mTabFragmentTitle = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        mVpFragmentPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);

        mContentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(), mContentFragmentList, mTitleList);
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

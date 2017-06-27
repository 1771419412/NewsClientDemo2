package com.example.newsclientdemo.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.MainAdapter;
import com.example.newsclientdemo.fragment.BaseFragment;
import com.example.newsclientdemo.fragment.WeatherFragment;
import com.example.newsclientdemo.fragment.ReadFragment;
import com.example.newsclientdemo.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends BaseActivity{

    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;

    private TextView tvS,tvR,tvT,tvM;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ViewPager mVPager;
    private MainAdapter mMainAdapter;

    private List<Fragment> mFragmentList;

    private String colorText="#52CC33";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtonViews();
        initNavigationView();
        initFragment();
        initViewPager();

    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
    private void initViewPager() {
        mVPager= (ViewPager) findViewById(R.id.top_pager);
        mMainAdapter=new MainAdapter(getSupportFragmentManager(),mFragmentList);
        mVPager.setAdapter(mMainAdapter);
        mVPager.setOffscreenPageLimit(5);
    }


    private void initFragment() {
        mFragmentList=new ArrayList<>();
        BaseFragment bf = new BaseFragment();
        ReadFragment rf=new ReadFragment();
        WeatherFragment wf=new WeatherFragment();
        UserFragment uf=new UserFragment();
        mFragmentList.add(bf);
        mFragmentList.add(rf);
        mFragmentList.add(wf);
        mFragmentList.add(uf);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_home:
                mVPager.setCurrentItem(0);
                mTvHome.setSelected(true);
                mTvBBS.setSelected(false);
                mTvPaly.setSelected(false);
                mTvMy.setSelected(false);
                tvS.setTextColor(Color.parseColor(colorText));
                tvR.setTextColor(Color.GRAY);
                tvT.setTextColor(Color.GRAY);
                tvM.setTextColor(Color.GRAY);
                break;

            case R.id.tv_bbs:
                mVPager.setCurrentItem(1);
                mTvHome.setSelected(false);
                mTvBBS.setSelected(true);
                mTvPaly.setSelected(false);
                mTvMy.setSelected(false);
                tvS.setTextColor(Color.GRAY);
                tvR.setTextColor(Color.parseColor(colorText));
                tvT.setTextColor(Color.GRAY);
                tvM.setTextColor(Color.GRAY);
                break;

            case R.id.tv_play:
                mVPager.setCurrentItem(2);
                mTvHome.setSelected(false);
                mTvBBS.setSelected(false);
                mTvPaly.setSelected(true);
                mTvMy.setSelected(false);
                tvS.setTextColor(Color.GRAY);
                tvR.setTextColor(Color.GRAY);
                tvT.setTextColor(Color.parseColor(colorText));
                tvM.setTextColor(Color.GRAY);
                break;

            case R.id.tv_my:
                mVPager.setCurrentItem(3);
                mTvHome.setSelected(false);
                mTvBBS.setSelected(false);
                mTvPaly.setSelected(false);
                mTvMy.setSelected(true);
                tvS.setTextColor(Color.GRAY);
                tvR.setTextColor(Color.GRAY);
                tvT.setTextColor(Color.GRAY);
                tvM.setTextColor(Color.parseColor(colorText));
                break;

        }


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
        tvS= (TextView) findViewById(R.id.tv_s);
        tvR= (TextView) findViewById(R.id.tv_r);
        tvT= (TextView) findViewById(R.id.tv_t);  
        tvM= (TextView) findViewById(R.id.tv_w);

        mTvHome.setSelected(true);
        tvS.setTextColor(Color.parseColor(colorText));

        mTvHome.setOnClickListener(this);
        mTvBBS.setOnClickListener(this);
        mTvMy.setOnClickListener(this);
        mTvPaly.setOnClickListener(this);
    }



}

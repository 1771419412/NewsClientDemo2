package com.example.newsclientdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.newsclientdemo.R;

public class VideoActivity extends BaseActivity {
    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initButtonViews();
        mTvPaly.setSelected(true);
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
}

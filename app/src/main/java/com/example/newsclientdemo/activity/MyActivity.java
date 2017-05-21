package com.example.newsclientdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsclientdemo.R;

public class MyActivity extends BaseActivity {
    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;
    private TextView mLogin;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initButtonViews();
        mTvMy.setSelected(true);
        initViews();

    }

    private void initViews() {
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("个人专场");
    }


    private void initButtonViews() {
        mTvHome = (LinearLayout) findViewById(R.id.tv_home);
        mTvBBS = (LinearLayout) findViewById(R.id.tv_bbs);
        mTvPaly = (LinearLayout) findViewById(R.id.tv_play);
        mTvMy = (LinearLayout) findViewById(R.id.tv_my);

        mLogin= (TextView) findViewById(R.id.tb_tv);

        mTvHome.setOnClickListener(this);
        mTvBBS.setOnClickListener(this);
        mTvMy.setOnClickListener(this);
        mTvPaly.setOnClickListener(this);
        mLogin.setOnClickListener(this);
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
            case R.id.tb_tv:
                goTo(LoginActivity.class);
                break;
        }
    }
}

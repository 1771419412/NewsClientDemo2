package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.util.SPUtil;

public class WelcomeActivity extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initHandler();
    }



    private void initHandler() {
        mHandler=new Handler();
        if(SPUtil.getIsFirstRun(this)){
            startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
            finish();
            SPUtil.setIsFirstRun(this,false);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }
            },2000);
        }
    }

}

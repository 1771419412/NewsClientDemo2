package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.newsclientdemo.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        /*Bmob.initialize(this,"16dd4015dd94e7586511a65d6972e9fc");*/
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0,0);
        super.onPause();
    }

    public void goTo(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        this.overridePendingTransition(0, 0);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                goTo(MainActivity.class);
                break;

            case R.id.tv_bbs:
                goTo(ReadActivity.class);
                break;


            case R.id.tv_play:
                goTo(VideoActivity.class);
                break;
            case R.id.tv_my:
                goTo(MyActivity.class);
                break;
        }
    }
}

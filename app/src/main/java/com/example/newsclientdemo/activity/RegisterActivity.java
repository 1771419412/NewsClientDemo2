package com.example.newsclientdemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.newsclientdemo.R;

public class RegisterActivity extends BaseActivity {
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("注册");
    }
}

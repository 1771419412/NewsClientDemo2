package com.example.newsclientdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newsclientdemo.R;

public class LoginActivity extends BaseActivity {
    private TextView mTitle;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

    }

    private void initViews() {
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("登录");
        mRegister= (TextView) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                goTo(RegisterActivity.class);
                break;
        }
    }
}

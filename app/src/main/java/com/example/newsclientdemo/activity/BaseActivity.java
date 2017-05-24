package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.newsclientdemo.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }




    //隐藏软件盘
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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

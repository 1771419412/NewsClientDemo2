package com.example.newsclientdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsclientdemo.R;

import cn.bmob.v3.BmobUser;

import static android.R.attr.key;

public class MyActivity extends BaseActivity {
    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;
    private TextView mLogin;
    private TextView mTitle;
    private TextView mOutLogin;
    private TextView mCommentLogin;
    private TextView mCollectionLogin;

    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        if(bundle!=null){
            mUsername = bundle.getString("username");
        }



        initButtonViews();
        mTvMy.setSelected(true);
        initViews();

    }

    private void initViews() {
        mTitle = (TextView) findViewById(R.id.title_tv);
        mTitle.setText("个人专场");

        mLogin = (TextView) findViewById(R.id.tb_tv);
        BmobUser user = BmobUser.getCurrentUser();
        if (user != null) {

            mLogin.setText(mUsername);
        } else {
            mLogin.setText("点击登录");
        }


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(LoginActivity.class);
                finish();
            }
        });
        mOutLogin = (TextView) findViewById(R.id.out_login);
        mOutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user != null) {
                    BmobUser.logOut();   //清除缓存用户对象
                    BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                    Toast.makeText(MyActivity.this, "成功退出", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyActivity.this, "你还未登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCommentLogin= (TextView) findViewById(R.id.comment_login);
        mCommentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                }else {

                    Intent intent = new Intent(MyActivity.this, NewsCommentActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                }
            }
        });
        mCollectionLogin= (TextView) findViewById(R.id.collection_login);
        mCollectionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(CollectionActivity.class);

            }
        });

    }
    private void dialogLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录");
        builder.setMessage("亲，你还没有登录，不可以评论喔！点击确认可以跳转登录界面");
        builder.setIcon(R.drawable.tou);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goTo(LoginActivity.class);
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

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

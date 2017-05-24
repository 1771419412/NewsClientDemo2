package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsclientdemo.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity {
    private TextView mTitle;
    private TextView mRegister;

    private EditText mLoginUser;
    private EditText mLoginPassword;
    private Button mLoginBtn;
    private TextView mLoginTv;

    private String LoginUserName;
    private String LoginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Intent intent=getIntent();
        LoginUserName=intent.getStringExtra("username");
        LoginPw=intent.getStringExtra("password");
        initViews();

    }

    private void initViews() {
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("登录");
        mLoginTv= (TextView) findViewById(R.id.tb_tv);
        mRegister= (TextView) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        mLoginUser= (EditText) findViewById(R.id.login_user);
        mLoginPassword= (EditText) findViewById(R.id.login_password);
        mLoginUser.setText(LoginUserName);
        mLoginPassword.setText(LoginPw);
        mLoginBtn= (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()){
            case R.id.register:
                goTo(RegisterActivity.class);
                break;
            case R.id.btn_login:
                // 获取用户输入的用户名和密码
                final String username = mLoginUser.getText().toString();
                String password = mLoginPassword.getText().toString();

                // 非空验证
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                BmobUser.loginByAccount(username, password, new LogInListener<BmobUser>() {

                    @Override
                    public void done(BmobUser user, BmobException e) {
                        if(user!=null){
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MyActivity.class);
                            //用Bundle携带数据
                            Bundle bundle=new Bundle();
                            bundle.putString("username",username);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "帐号或密码输入有误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        }
    }
}

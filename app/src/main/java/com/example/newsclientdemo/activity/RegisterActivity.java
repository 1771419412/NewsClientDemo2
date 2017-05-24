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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {
    private TextView mTitle;
    private EditText mPhone;
    private EditText mRegPassword;
    private EditText mEmail;
    private Button mRegBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        initView();
    }
    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

  /*  *//**
     * 验证手机号码
     * @param phone
     * @return
     *//*
    public static boolean checkMobileNumber(String phone){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }*/
    private void initView() {
        mTitle= (TextView) findViewById(R.id.title_tv);
        mTitle.setText("注册");
        mPhone= (EditText) findViewById(R.id.reg_user);
        mRegPassword= (EditText) findViewById(R.id.reg_password);
        mEmail= (EditText) findViewById(R.
                id.reg_que_password);
        mRegBtn= (Button) findViewById(R.id.btn_reg);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的用户名和密码
                final String username = mPhone.getText().toString();
                final String password = mRegPassword.getText().toString();
                String email=mEmail.getText().toString();

                // 非空验证
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ){
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkEmail(email)){
                    Toast.makeText(RegisterActivity.this, "你输的邮箱格式有误，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if(!checkMobileNumber(username)){
                    Toast.makeText(RegisterActivity.this, "你输的手机格式有误，请重新输入", Toast.LENGTH_SHORT).show();
                    return;

                }*/
                // 使用BmobSDK提供的注册功能
                BmobUser user = new BmobUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.putExtra("username",username);
                            intent.putExtra("password",password);
                            startActivity(intent);
                        }else {

                        }
                    }
                });
            }
        });
    }
}

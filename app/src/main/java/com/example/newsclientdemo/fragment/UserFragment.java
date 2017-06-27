package com.example.newsclientdemo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import com.example.newsclientdemo.R;
import com.example.newsclientdemo.activity.CollectionActivity;
import com.example.newsclientdemo.activity.LoginActivity;
import com.example.newsclientdemo.activity.NewsCommentActivity;
import com.example.newsclientdemo.data.NewsUserData;

import cn.bmob.v3.BmobUser;

import static android.R.attr.key;

/**
 * Created by 雪无痕 on 2017/5/26.
 */

public class UserFragment extends Fragment {
    private LinearLayout mTvHome;
    private LinearLayout mTvBBS;
    private LinearLayout mTvMy;
    private LinearLayout mTvPaly;
    private Button mLogin;
    private TextView mTitle;
    private TextView mOutLogin;
    private TextView mCommentLogin;
    private TextView mCollectionLogin;

    private String mUsername;
    private String bgTitle="#6E6E6E";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        //新页面接收数据
        Bundle bundle = getActivity().getIntent().getExtras();
        //接收name值
        if (bundle != null) {
            mUsername = bundle.getString("username");
        }
        NewsUserData userData=new NewsUserData();
        userData.setUsername(mUsername);
        String username = userData.getUsername();
        initViews(view);
        BmobUser user=BmobUser.getCurrentUser();
        if(user!=null){
            mLogin.setText(username);
        }else {
            mLogin.setText("点击登录");
        }

        mTitle = (TextView) view.findViewById(R.id.title_tv);
        mTitle.setText("个人专场");
        mTitle.setBackgroundColor(Color.parseColor(bgTitle));
        return view;
    }


    private void initViews(View view) {
        mLogin = (Button) view.findViewById(R.id.tb_tv);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        mOutLogin = (TextView) view.findViewById(R.id.out_login);
        mOutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user != null) {
                    BmobUser.logOut();   //清除缓存用户对象
                    BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                    Toast.makeText(getActivity(), "成功退出", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "你还未登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCommentLogin = (TextView) view.findViewById(R.id.comment_login);
        mCommentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                } else {

                    Intent intent = new Intent(getActivity(), NewsCommentActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                }
            }
        });
        mCollectionLogin = (TextView) view.findViewById(R.id.collection_login);
        mCollectionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);

            }
        });

    }

    private void dialogLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("亲，你还没有登录，不可以评论喔！点击确认可以跳转登录界面");
        builder.setIcon(R.drawable.tou);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
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
}

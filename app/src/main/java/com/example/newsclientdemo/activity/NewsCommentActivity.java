package com.example.newsclientdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.NewsCommentAdapter;
import com.example.newsclientdemo.data.NewsUserData;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class NewsCommentActivity extends AppCompatActivity {
    private List<NewsUserData> mNewsCommentDataList=new ArrayList<>();
    private NewsCommentAdapter mNewsCommentAdapter;
    private RecyclerView mRecyclerView;
    private ImageView mBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_comment);
        /*String username = BmobUser.getCurrentUser().getUsername();
        BmobQuery<NewsUserData> query = new BmobQuery<>();
        query.getObject(username, new QueryListener<NewsUserData>() {
            @Override
            public void done(NewsUserData newsUserData, BmobException e) {
                if (e == null) {
                    String username1 = newsUserData.getUsername();
                    String type = newsUserData.getType();
                    String date = newsUserData.getDate();
                    String comments = newsUserData.getComments();
                }
            }
        });*/
        query();
        /*for (int i = 0; i < mNewsCommentDataList.size(); i++) {
            NewsUserData b=mNewsCommentDataList.get(i);
            Log.e("jjy", "onCreate: "+b.getUsername() );
        }*/
        initViews();

    }

    private void initViews() {
        mBackImageView= (ImageView) findViewById(R.id.back_image);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void query() {
        Toast.makeText(this, "欢迎来到评论页面", Toast.LENGTH_LONG).show();
        String key = getIntent().getStringExtra("key");
        //查询所有的游戏得分记录
        String bql = "select * from NewsUserData";

        BmobQuery<NewsUserData> query = new BmobQuery<NewsUserData>();
        //设置查询的SQL语句
        query.setSQL(bql);
        query.doSQLQuery(new SQLQueryListener<NewsUserData>() {

            @Override
            public void done(BmobQueryResult<NewsUserData> result, BmobException e) {
                if (e == null) {
                    List<NewsUserData> list = (List<NewsUserData>) result.getResults();
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            mNewsCommentDataList.add(list.get(i));
                        }
                        Log.i("smile", "查询成功，"+list.size());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initRecyclerView();
                            }
                        });
                    } else {
                        Log.i("smile", "查询成功，无数据返回");
                    }
                } else {
                    Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mNewsCommentAdapter = new NewsCommentAdapter(mNewsCommentDataList, getApplicationContext());
        mRecyclerView.setAdapter(mNewsCommentAdapter);

    }
}

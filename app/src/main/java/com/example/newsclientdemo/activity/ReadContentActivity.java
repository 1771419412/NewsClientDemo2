package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.data.NovelContentBean;
import com.example.newsclientdemo.util.ParserWeb;

public class ReadContentActivity extends AppCompatActivity {

    private NovelContentBean novelContentBean;
    private TextView tv_title;
    private TextView tv_name;
    private TextView tv_author;
    private TextView tv_time;
    private TextView tv_wd;
    private TextView tv_con;
    private Button bt_pre;
    private Button bt_next;

    private Handler mHandler;

    private final int MSG_CON=1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content);
        initViews();
        initHandle();
        final String path=getIntent().getStringExtra("path");
        new Thread(){
            @Override
            public void run() {
                super.run();
                novelContentBean= ParserWeb.parser_nol(path);
                if(novelContentBean!=null){
                    mHandler.sendEmptyMessage(MSG_CON);
                }
            }
        }.start();
    }

    private void initHandle() {
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==MSG_CON){

                    tv_title.setText(novelContentBean.getTitle());
                    tv_name.setText(novelContentBean.getNovel_name());
                    tv_author.setText(novelContentBean.getAuthor());
                    tv_time.setText(novelContentBean.getTime());
                    tv_wd.setText(novelContentBean.getWdnumber());
                    tv_con.setText(" \u3000\u3000"+ novelContentBean.getNv_content().toString());

                    //如果上一章不存在
                    if(novelContentBean.getPre_link()=="" || novelContentBean.getPre_link()==null){
                        bt_pre.setVisibility(View.INVISIBLE);
                        bt_pre.setClickable(false);
                    }
                    //如果下一章不存在
                    if(novelContentBean.getNext_link()=="" ||novelContentBean.getNext_link()==null){
                        bt_next.setVisibility(View.INVISIBLE);
                        bt_next.setClickable(false);
                    }

                    //上一章的点击事件
                    bt_pre.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(ReadContentActivity.this,ReadContentActivity.class);
                            intent.putExtra("path",novelContentBean.getPre_link());
                            startActivity(intent);
                            finish();
                        }
                    });

                    //下一章的点击事件
                    bt_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(ReadContentActivity.this,ReadContentActivity.class);
                            intent.putExtra("path",novelContentBean.getNext_link());
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        };
    }

    private void initViews() {
        tv_title= (TextView) findViewById(R.id.read_con_title);
        tv_name= (TextView) findViewById(R.id.read_con_name);
        tv_author= (TextView) findViewById(R.id.read_con_author);
        tv_time= (TextView) findViewById(R.id.read_con_time);
        tv_wd= (TextView) findViewById(R.id.read_con_num);
        tv_con= (TextView) findViewById(R.id.read_con_con);
        bt_pre= (Button) findViewById(R.id.read_btn_pre);
        bt_next= (Button) findViewById(R.id.read_btn_next);
    }
}

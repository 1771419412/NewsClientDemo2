package com.example.newsclientdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.BaseNewstAdapter;
import com.example.newsclientdemo.data.NewsData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 雪无痕 on 2017/5/14.
 */

public class NewsFragment extends Fragment {

    private static final int MSG_GET_NEWS = 1001;
    private TextView mTvContent;
    private String mContent;

 /*   private BaseNewstAdapter mAdapter;*/

    private int mType;
    private RecyclerView mRecyclerView;
    private BaseNewstAdapter mBaseNewstAdapter;
    private List<NewsData.ResultBean.NewsDataBase> mNewsList=new ArrayList<>();
    private Handler mHandler;

    private final String UrlTop="http://v.juhe.cn/toutiao/index?type=top&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlJun="http://v.juhe.cn/toutiao/index?type=junshi&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlTi="http://v.juhe.cn/toutiao/index?type=tiyu&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlYu="http://v.juhe.cn/toutiao/index?type=yule&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlKe="http://v.juhe.cn/toutiao/index?type=keji&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlShe="http://v.juhe.cn/toutiao/index?type=shehui&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlGuonei="http://v.juhe.cn/toutiao/index?type=guonei&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";
    private final String UrlGuoji="http://v.juhe.cn/toutiao/index?type=guoji&key=c1f1d8e217b8d18f7fa8dbf2db5e981f";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_fragment, container, false);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        mBaseNewstAdapter=new BaseNewstAdapter(mNewsList,getContext());
        mRecyclerView.setAdapter(mBaseNewstAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*mTvContent = (TextView) view.findViewById(R.id.tv_fragment);
        mTvContent.setText(mContent);*/
       /* initViews();*/
       /* mAdapter.notifyDataSetChanged();*/
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType=getArguments().getInt("NEWSTYPE");

        intiHandler();
        getNewsFromJuHe();



    }

   /*private void initViews() {
        mAdapter= new BaseNewstAdapter(mNewsList,getContext());
        mAdapter.setOnItemClickListener(new BaseNewstAdapter.OnItemClickListen() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent=new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("url",mNewsList.get(position).getUrl());
                startActivity(intent);
            }
        });
    }*/


    private void intiHandler() {
        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==MSG_GET_NEWS){
                    mBaseNewstAdapter.changData(mNewsList);
                    return true;
                }
                return false;
            }
        });
    }

    private void getNewsFromJuHe() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request ;
        switch (mType){

            case 1:
                request=new Request.Builder().url(UrlTop).build();
                break;

            case 2:
                request=new Request.Builder().url(UrlJun).build();
                break;

            case 3:
                request=new Request.Builder().url(UrlTi).build();
                break;

            case 4:
                request=new Request.Builder().url(UrlYu).build();
                break;

            case 5:
                request=new Request.Builder().url(UrlKe).build();
                break;

            case 6:
                request=new Request.Builder().url(UrlShe).build();
                break;

            case 7:
                request=new Request.Builder().url(UrlGuonei).build();
                break;

            case 8:
                request=new Request.Builder().url(UrlGuoji).build();
                break;

            default:
                request=new Request.Builder().url(UrlTop).build();
                break;

        }
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AAA", "GET DATA FAILED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                NewsData newsData=gson.fromJson(response.body().string(),NewsData.class);
                mNewsList=newsData.getResult().getData();
                mHandler.sendEmptyMessage(MSG_GET_NEWS);

            }
        });
    }

    /*public void setTvContent(String content) {

            this.mContent = content;

    }*/



}

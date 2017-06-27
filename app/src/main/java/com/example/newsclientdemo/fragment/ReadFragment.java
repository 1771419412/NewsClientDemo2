package com.example.newsclientdemo.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.adapter.ReadAdapter;
import com.example.newsclientdemo.data.NovelBean;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 雪无痕 on 2017/5/26.
 */

public class ReadFragment extends Fragment{
    private final int  MSG_READ=123;
    private String YUE="#999999";

    private RecyclerView mRecyclerView;
    private ReadAdapter mReadAdapter;
    private List<NovelBean> mNovelBeanList;
    private DisplayImageOptions mOptions;
    private TextView mTitle;

    private Handler mHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        final View view=inflater.inflate(R.layout.fragment_read,container,false);
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getActivity())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(30 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();


        mOptions=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
        ImageLoader.getInstance().init(configuration);
       mHandler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==MSG_READ){
                    //获取文章列表成功,更新listview
                    //mRecyclerView.setAdapter(new ReadAdapter(ReadActivity.this,mNovelBeanList,mOptions));
                    initRecyclerView(view);
                }
            }
        };
        mTitle= (TextView) view.findViewById(R.id.title_tv);
        mTitle.setText("阅读专场");
        mTitle.setBackgroundColor(Color.parseColor(YUE));
        
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        //从网页爬取数据
        http_getnovel();
        //recyclerView的item点击事件

    }
    private void http_getnovel() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //获取连接http://www.zongheng.com/category/3.html    http://book.zongheng.com/category/1.html
                    Connection connection= Jsoup.connect("http://book.zongheng.com/category/1.html");
                    //设置超时

                    connection.timeout(10000);
                    Document document=connection.get();
                    Elements detail=document.select("div.detail");

                    mNovelBeanList=new ArrayList<NovelBean>();
                    for (Element element:detail){
                        String novel_image=element.getElementsByTag("img").first().attr("src").trim();
                        String novel_name=element.getElementsByTag("h3").first().text().trim();
                        Elements p_element=element.getElementsByTag("p");
                        String novel_author=p_element.get(0).text().trim();
                        String novel_type=p_element.get(1).text().trim();
                        String novel_path=p_element.get(2).getElementsByTag("a").attr("href").trim();

                        NovelBean novelBean=new NovelBean(novel_name,novel_image,novel_author,novel_type,novel_path);
                        mNovelBeanList.add(novelBean);
                    }
                    mHandler.sendEmptyMessage(MSG_READ);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }
    private void initRecyclerView(View view) {
        mRecyclerView= (RecyclerView)view. findViewById(R.id.read_recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(manager);
        mReadAdapter=new ReadAdapter(getApplicationContext(),mNovelBeanList,mOptions);
        mRecyclerView.setAdapter(mReadAdapter);
    }


}

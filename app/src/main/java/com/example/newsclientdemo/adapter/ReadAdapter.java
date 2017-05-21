package com.example.newsclientdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.activity.ReadContentActivity;
import com.example.newsclientdemo.data.NovelBean;
import com.example.newsclientdemo.util.ParserWeb;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 雪无痕 on 2017/5/20.
 */

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private List<NovelBean> mNovelBeanList=new ArrayList<>();
    private final Context mContext;
    private DisplayImageOptions mOptions;
    private LayoutInflater mInflater;

    public ReadAdapter(Context context,List<NovelBean> novelBeanList,DisplayImageOptions imageOptions){
        this.mNovelBeanList=novelBeanList;
        this.mContext=context;
        this.mOptions=imageOptions;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_read,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        NovelBean novelBean=mNovelBeanList.get(position);
        ImageLoader.getInstance().displayImage(novelBean.getNovel_image(),holder.Novel_image,mOptions);
        holder.Novel_name.setText(novelBean.getNovel_name());
        holder.Novel_author.setText(novelBean.getNovel_author());
        holder.Novel_type.setText(novelBean.getNovel_type());
        holder.Novel_path.setText("【阅读】");
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        NovelBean novelBean1=mNovelBeanList.get(position);
                        String url=novelBean1.getNovel_path();
                        String path= ParserWeb.parser_web(url);
                        Intent intent=new Intent(mContext, ReadContentActivity.class);
                        intent.putExtra("path",path);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }.start();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNovelBeanList.size();
    }




    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView Novel_image;
        TextView Novel_name;
        TextView Novel_author;
        TextView Novel_type;
        TextView Novel_path;
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            Novel_image= (ImageView) itemView.findViewById(R.id.novel_image);
            Novel_name= (TextView) itemView.findViewById(R.id.novel_name);
            Novel_author= (TextView) itemView.findViewById(R.id.novel_author);
            Novel_type= (TextView) itemView.findViewById(R.id.novel_type);
            Novel_path= (TextView) itemView.findViewById(R.id.novel_path);
            mCardView= (CardView) itemView.findViewById(R.id.readcardview);
        }
    }
}

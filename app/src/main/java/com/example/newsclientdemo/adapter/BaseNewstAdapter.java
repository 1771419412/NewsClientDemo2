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

import com.bumptech.glide.Glide;
import com.example.newsclientdemo.R;
import com.example.newsclientdemo.activity.DetailsActivity;
import com.example.newsclientdemo.data.NewsData;

import java.util.List;

/**
 * Created by 雪无痕 on 2017/5/18.
 */

public class BaseNewstAdapter extends RecyclerView.Adapter<BaseNewstAdapter.ViewHolder>{
    private List<NewsData.ResultBean.NewsDataBase> mNewsList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public BaseNewstAdapter(List<NewsData.ResultBean.NewsDataBase> newsList, Context context) {
        this.mNewsList = newsList;
        this.mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.base_fragment_item,parent,false);
        ViewHolder holder=new ViewHolder(view) ;
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        NewsData.ResultBean.NewsDataBase newsDataBase=mNewsList.get(position);
        holder.mTitle.setText(newsDataBase.getTitle());
        Glide.with(holder.mImageView.getContext())
                .load(newsDataBase.getThumbnail_pic_s())
                .into(holder.mImageView);
        holder.mType.setText(newsDataBase.getAuthor_name());
        holder.mTime.setText(newsDataBase.getDate());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, DetailsActivity.class);
                intent.putExtra("url",mNewsList.get(position).getUrl());
                mContext.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void changData(List< NewsData.ResultBean.NewsDataBase> newsList) {
        this.mNewsList=newsList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTitle;
        TextView mType;
        TextView mTime;
        CardView mCardView;


        public ViewHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.item_imag);
            mTitle= (TextView) itemView.findViewById(R.id.item_title);
            mType= (TextView) itemView.findViewById(R.id.item_type);
            mTime= (TextView) itemView.findViewById(R.id.item_time);
            mCardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }


}

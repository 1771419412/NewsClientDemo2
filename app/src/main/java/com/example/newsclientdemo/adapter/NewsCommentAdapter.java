package com.example.newsclientdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.data.NewsUserData;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by 雪无痕 on 2017/5/22.
 */

public class NewsCommentAdapter extends RecyclerView.Adapter<NewsCommentAdapter.ViewHolder>{

    private List<NewsUserData> mNewsCommentDataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NewsCommentAdapter(List<NewsUserData> newsCommentDataList,Context context){
        this.mNewsCommentDataList=newsCommentDataList;
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(mContext);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.item_news_comment,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsUserData commentData=mNewsCommentDataList.get(position);
        String username= BmobUser.getCurrentUser().getUsername();

        holder.mCommentUserName.setText(username);
        holder.mCommentType.setText(commentData.getTitle());
        holder.mCommentDate.setText(commentData.getDate());
        holder.mCommentContent.setText(commentData.getComments());
        Log.e("aaaa", username );

    }

    @Override
    public int getItemCount() {
        return mNewsCommentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mCommentUserName;
        TextView mCommentType;
        TextView mCommentDate;
        TextView mCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mCommentUserName= (TextView) itemView.findViewById(R.id.comment_username);
            mCommentType= (TextView) itemView.findViewById(R.id.comment_type);
            mCommentDate= (TextView) itemView.findViewById(R.id.comment_date);
            mCommentContent= (TextView) itemView.findViewById(R.id.comment_content);
        }
    }
}

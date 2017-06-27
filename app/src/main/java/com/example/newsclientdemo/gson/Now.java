package com.example.newsclientdemo.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 雪无痕 on 2017/6/11.
 */
//当前天气信息
public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;


    public class More{
        @SerializedName("txt")
        public String info;
    }
}

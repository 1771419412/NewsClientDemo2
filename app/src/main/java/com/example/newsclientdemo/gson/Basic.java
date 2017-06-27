package com.example.newsclientdemo.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 雪无痕 on 2017/6/11.
 */
//城市基本信息
public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}

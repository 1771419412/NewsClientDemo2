package com.example.newsclientdemo.gson;

/**
 * Created by 雪无痕 on 2017/6/11.
 */
//当前的空气质量
public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}

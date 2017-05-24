package com.example.newsclientdemo.application;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 雪无痕 on 2017/5/20.
 */

public class BaseApplication extends Application{

    private static BaseApplication instance;
    private static final String APP_KEY = "1e1fc8d8da970";

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "16dd4015dd94e7586511a65d6972e9fc");
        ShareSDK.initSDK(this, APP_KEY);
        instance = this;
        initTbs();
    }

    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.i("aa", "onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.i("aa", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.i("aa", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.i("aa", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}

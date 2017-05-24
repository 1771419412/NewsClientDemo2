package com.example.newsclientdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsclientdemo.R;
import com.example.newsclientdemo.data.CollectionData;
import com.example.newsclientdemo.data.NewsUserData;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class DetailsActivity extends BaseActivity {
    private WebView mWebView;
    private String url;
    private String key;
    private String title;
    private String type;
    private String date;
    private EditText mEdtComment;
    private ImageButton mBtnImageComment;
    private ImageButton mBtmShareComment;

    private Bundle params;
    private TextView mTvFa;
    private ImageView mIsCollection;


    public static final String URL_EXTRA = "URL_EXTRA";
    public static final String KEY_EXTRA = "KEY_EXTRA";
    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    public static final String TYPE_EXTRA = "TYPE_EXTRA";
    public static final String DATE_EXTRA = "DATE_EXTRA";
    private String mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        url = extras.getString(URL_EXTRA);
        key = extras.getString(KEY_EXTRA);
        title = extras.getString(TITLE_EXTRA);
        date = extras.getString(DATE_EXTRA);
        type=extras.getString(TYPE_EXTRA);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        query();
        intiX5(url);
        initViews();

    }

    private void query() {
        String bql = "select * from CollectionData";
        BmobQuery<CollectionData> query=new BmobQuery<CollectionData>();
//设置查询的SQL语句
        query.setSQL(bql);
        query.doSQLQuery(new SQLQueryListener<CollectionData>(){

            @Override
            public void done(BmobQueryResult<CollectionData> result, BmobException e) {
                if(e ==null){
                    List<CollectionData> list = (List<CollectionData>) result.getResults();
                    if(list!=null && list.size()>0){

                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
    }

    private void initViews() {
        mEdtComment = (EditText) findViewById(R.id.edt_comment);
        mBtnImageComment = (ImageButton) findViewById(R.id.tv_comment);
        mBtmShareComment= (ImageButton) findViewById(R.id.share_comment);
        mTvFa = (TextView) findViewById(R.id.tv_fasong);
        mTvFa.setVisibility(View.GONE);

        mEdtComment.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mTvFa.setVisibility(View.VISIBLE);
                mBtnImageComment.setVisibility(View.GONE);
                mBtmShareComment.setVisibility(View.GONE);
                return false;
            }
        });


        mTvFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                } else {
                    String phone = BmobUser.getCurrentUser().getUsername();
                    mComment = mEdtComment.getText().toString().trim();
                    NewsUserData userData = new NewsUserData();
                    userData.setUsername(phone);
                    userData.setUniquekey(key);
                    userData.setTitle(title);
                    /*userData.setTitle(type);*/
                    userData.setDate(date);
                    userData.setComments(mComment);
                    userData.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.e("AAA", "save comment success");
                            }
                        }
                    });
                }

                //清空编辑框
                mEdtComment.getEditableText().clear();
                mBtnImageComment.setVisibility(View.VISIBLE);
                mBtmShareComment.setVisibility(View.VISIBLE);
                mTvFa.setVisibility(View.GONE);

            }
        });
        mBtnImageComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                }else {

                    Intent intent = new Intent(DetailsActivity.this, NewsCommentActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                }

            }
        });

        mBtmShareComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                }else{
                    share();
                }

            }
        });




        mIsCollection= (ImageView) findViewById(R.id.image_btn_collection);
        mIsCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null) {
                    dialogLogin();
                }else {
                    String username = BmobUser.getCurrentUser().getUsername();
                    CollectionData collectionData=new CollectionData();
                    collectionData.setUserName(username);
                    collectionData.setNewsKey(key);
                    collectionData.setNewsTitle(title);
                    collectionData.setNewsUrl(url);
                    collectionData.setNewsType(type);
                    collectionData.setCollection(true);
                    collectionData.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Log.e("CCCCCCCCCC", "save comment success");

                                mIsCollection.setImageResource(R.mipmap.collcation_02);
                                Toast.makeText(DetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });
    }

    private void share() {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(title);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(mComment);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);

    }

    private void dialogLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录");
        builder.setMessage("亲，你还没有登录!点击确认可以跳转登录界面!");
        builder.setIcon(R.drawable.tou);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goTo(LoginActivity.class);
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void intiX5(String url) {
        
        mWebView = (WebView) findViewById(R.id.web_context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.collection_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_item:
                Toast.makeText(this, "You clicked 分享", Toast.LENGTH_SHORT).show();
                shareToQQ();
                break;
            default:
        }
        return true;
    }

    private void shareToQQ() {


    }
}

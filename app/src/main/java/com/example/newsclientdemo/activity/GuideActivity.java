package com.example.newsclientdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.newsclientdemo.R;

public class GuideActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    private TextView mTextView;

    Animation leftInAnimation;
    Animation leftOutAnimation;
    Animation rightInAnimation;
    Animation rightOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();
    }

    private void initViews() {
        mViewFlipper= (ViewFlipper) findViewById(R.id.view_flipper);
        mGestureDetector=new GestureDetector(this);

        //动画效果
        leftInAnimation= AnimationUtils.loadAnimation(this,R.anim.left_in);
        leftInAnimation= AnimationUtils.loadAnimation(this,R.anim.left_out);
        leftInAnimation= AnimationUtils.loadAnimation(this,R.anim.right_in);
        leftInAnimation= AnimationUtils.loadAnimation(this,R.anim.right_out);

        mTextView= (TextView) findViewById(R.id.start_tv);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private ImageView getImageView(int id){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(id);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.mGestureDetector.onTouchEvent(event);//touch事件交给手势处理。
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX()-e2.getX()>120){
            mViewFlipper.setInAnimation(leftInAnimation);
            mViewFlipper.setInAnimation(leftOutAnimation);
            mViewFlipper.showNext();//向右滑动
            return true;
        }else if(e1.getX()-e2.getX()<-120){
            mViewFlipper.setInAnimation(rightInAnimation);
            mViewFlipper.setInAnimation(rightOutAnimation);
            mViewFlipper.showPrevious();//向左滑动
            return true;
        }

        return false;
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


}

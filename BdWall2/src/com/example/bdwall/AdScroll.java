package com.example.bdwall;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AdScroll extends LinearLayout  {

	    ViewPager vp;
	    ImageView img1;
	    ImageView img2;
	    ImageView img3;
	    ImageView img4;

	    Timer timer;
	    TimerTask timetask;
	    int index=0;//当前页面的位置
	
	public AdScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		 initView();
	}
	
	 /*
    初始化布局页面
     */
    private void initView() {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.ad_scroll,this);
        findView(view);
        //设置滑动事件
        vp.setOnPageChangeListener(new MyPageChangeLister());
     
    }
   
    private void findView(View view) {
        vp= (ViewPager) findViewById(R.id.myAdViewPage);
        img1= (ImageView) findViewById(R.id.img1);
        img2= (ImageView) findViewById(R.id.img2);
        img3= (ImageView) findViewById(R.id.img3);
        img4= (ImageView) findViewById(R.id.img4);

    }

    private  class MyPageChangeLister implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        	 if (position==4){
                 index=0;
             }
        } 

        @Override
        public void onPageSelected(int position) {
              index=position;
              if (index==4){
                  index=0;
              }
              Bundle bundle=new Bundle();
              bundle.putInt("index",index);
              Message msg=new Message();
              msg.setData(bundle);
              msg.what=100;
              handler.sendMessage(msg);
             
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    
    public void setPageFromTime(int delayTime){
        timer=new Timer();
        timetask=new TimerTask() {
            @Override
            public void run() {
                //业务逻辑
               // initPic(index);
                Bundle bundle=new Bundle();
                bundle.putInt("index",index);
                Message msg=new Message();
                msg.setData(bundle);
                msg.what=100;
                handler.sendMessage(msg);

                index++;
                if (index==4){
                    index=0;
                }
            }
        };
        timer.schedule(timetask,1000,delayTime);

    }
    
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    int i=msg.getData().getInt("index");
                    initPic(i);
                    break;
            }
        }
    };
    protected void initPic(int index){
        vp.setCurrentItem(index);//更新页面
        switch (index){//更新 下面的 点
            case 0:
                img1.setBackgroundResource(R.drawable.point_selected);
                img2.setBackgroundResource(R.drawable.point_normal);
                img3.setBackgroundResource(R.drawable.point_normal);
                img4.setBackgroundResource(R.drawable.point_normal);
                break;
            case 1:
                img1.setBackgroundResource(R.drawable.point_normal);
                img2.setBackgroundResource(R.drawable.point_selected);
                img3.setBackgroundResource(R.drawable.point_normal);
                img4.setBackgroundResource(R.drawable.point_normal);
                break;
            case 2:
                img1.setBackgroundResource(R.drawable.point_normal);
                img2.setBackgroundResource(R.drawable.point_normal);
                img3.setBackgroundResource(R.drawable.point_selected);
                img4.setBackgroundResource(R.drawable.point_normal);
                break;
            case 3:
                img1.setBackgroundResource(R.drawable.point_normal);
                img2.setBackgroundResource(R.drawable.point_normal);
                img3.setBackgroundResource(R.drawable.point_normal);
                img4.setBackgroundResource(R.drawable.point_selected);
                break;



        }

    }
    public ViewPager getViewPage(){

        return  vp;
    }
}

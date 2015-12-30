package com.example.bdwall;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MybottomLayout extends LinearLayout {
     RelativeLayout homeLayout;
    RelativeLayout selectLayout;
    RelativeLayout searchLayout;
    RelativeLayout locationLayout;
    RelativeLayout settingLayout;

    LayoutInflater inflater;//布局管理器


    public MybottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    private void initView() {
        inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.layout_bottom,this);
        findView(view);
        
        initData();
        setonClick();
    }


    /*
    初始化
     */
    private void initData() {
        homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home);
        TextView tv01= (TextView) homeLayout.findViewById(R.id.tabText);
        tv01.setText("首页");
        tv01.setTextColor(Color.WHITE);

        selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage);
        TextView tv02= (TextView) selectLayout.findViewById(R.id.tabText);
        tv02.setText("选择");
        tv02.setTextColor(Color.WHITE);


        searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search);
        TextView tv03= (TextView) searchLayout.findViewById(R.id.tabText);
        tv03.setText("搜索");
        tv03.setTextColor(Color.WHITE);

        locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find);
        TextView tv04= (TextView) locationLayout.findViewById(R.id.tabText);
        tv04.setText("本地");
        tv04.setTextColor(Color.WHITE);

        settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more);
        TextView tv05= (TextView) settingLayout.findViewById(R.id.tabText);
        tv05.setText("设置");
        tv05.setTextColor(Color.WHITE);
    }

    /*
    设置点击事件
     */
    private void setonClick() {
        homeLayout.setOnClickListener(new listener());
        locationLayout.setOnClickListener(new listener());
        selectLayout.setOnClickListener(new listener());
        searchLayout.setOnClickListener(new listener());
        settingLayout.setOnClickListener(new listener());
    }

   public   interface  ICallbackListener{
        public void  click(int id);
	   
    }
    ICallbackListener callbackListener=null;

    int myid;
    public  void setOnCallbackListener(ICallbackListener cbl){
        this.callbackListener=cbl;

    }
    /*
    tab点击事件处理
     */
    private class listener implements OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.homelayout:
                    //点击之后更改图片颜色
                    initPic(0);
                    //切换页面
                    break;
                case R.id.selectlayout:
                    initPic(1);
                    break;
                case R.id.searchlayout:
                    initPic(2);
                    break;
                case R.id.locationlayout:
                    initPic(3);
                    break;
                case R.id.settinglayout:
                    initPic(4);
                    break;


            }
            //回传 点击的 ID
            callbackListener.click(view.getId());
        }
    }


    public  void initPic(int i){
       switch (i){
           case 0:
               homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home_down);
               TextView tv01= (TextView) homeLayout.findViewById(R.id.tabText);
               tv01.setText("首页");
               tv01.setTextColor(Color.BLUE);

               selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage);
               TextView tv02= (TextView) selectLayout.findViewById(R.id.tabText);
               tv02.setText("选择");
               tv02.setTextColor(Color.WHITE);


               searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search);
               TextView tv03= (TextView) searchLayout.findViewById(R.id.tabText);
               tv03.setText("搜索");
               tv03.setTextColor(Color.WHITE);

               locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find);
               TextView tv04= (TextView) locationLayout.findViewById(R.id.tabText);
               tv04.setText("本地");
               tv04.setTextColor(Color.WHITE);

               settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more);
               TextView tv05= (TextView) settingLayout.findViewById(R.id.tabText);
               tv05.setText("设置");
               tv05.setTextColor(Color.WHITE);
               break;

           case 1:
               homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home);
               TextView tv11= (TextView) homeLayout.findViewById(R.id.tabText);
               tv11.setText("首页");
               tv11.setTextColor(Color.WHITE);

               selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage_down);
               TextView tv12= (TextView) selectLayout.findViewById(R.id.tabText);
               tv12.setText("选择");
               tv12.setTextColor(Color.BLUE);


               searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search);
               TextView tv13= (TextView) searchLayout.findViewById(R.id.tabText);
               tv13.setText("搜索");
               tv13.setTextColor(Color.WHITE);

               locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find);
               TextView tv14= (TextView) locationLayout.findViewById(R.id.tabText);
               tv14.setText("本地");
               tv14.setTextColor(Color.WHITE);

               settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more);
               TextView tv15= (TextView) settingLayout.findViewById(R.id.tabText);
               tv15.setText("设置");
               tv15.setTextColor(Color.WHITE);
               break;
           case 2:
               homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home);
               TextView tv21= (TextView) homeLayout.findViewById(R.id.tabText);
               tv21.setText("首页");
               tv21.setTextColor(Color.WHITE);

               selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage);
               TextView tv22= (TextView) selectLayout.findViewById(R.id.tabText);
               tv22.setText("选择");
               tv22.setTextColor(Color.WHITE);


               searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search_down);
               TextView tv23= (TextView) searchLayout.findViewById(R.id.tabText);
               tv23.setText("搜索");
               tv23.setTextColor(Color.BLUE);

               locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find);
               TextView tv24= (TextView) locationLayout.findViewById(R.id.tabText);
               tv24.setText("本地");
               tv24.setTextColor(Color.WHITE);

               settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more);
               TextView tv25= (TextView) settingLayout.findViewById(R.id.tabText);
               tv25.setText("设置");
               tv25.setTextColor(Color.WHITE);
               break;
           case 3:
               homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home);
               TextView tv31= (TextView) homeLayout.findViewById(R.id.tabText);
               tv31.setText("首页");
               tv31.setTextColor(Color.WHITE);

               selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage);
               TextView tv32= (TextView) selectLayout.findViewById(R.id.tabText);
               tv32.setText("选择");
               tv32.setTextColor(Color.WHITE);


               searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search);
               TextView tv33= (TextView) searchLayout.findViewById(R.id.tabText);
               tv33.setText("搜索");
               tv33.setTextColor(Color.WHITE);

               locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find_down);
               TextView tv34= (TextView) locationLayout.findViewById(R.id.tabText);
               tv34.setText("本地");
               tv34.setTextColor(Color.BLUE);

               settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more);
               TextView tv35= (TextView) settingLayout.findViewById(R.id.tabText);
               tv35.setText("设置");
               tv35.setTextColor(Color.WHITE);
               break;
           case 4:
               homeLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_home);
               TextView tv41= (TextView) homeLayout.findViewById(R.id.tabText);
               tv41.setText("首页");
               tv41.setTextColor(Color.WHITE);

               selectLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_manage);
               TextView tv42= (TextView) selectLayout.findViewById(R.id.tabText);
               tv42.setText("选择");
               tv42.setTextColor(Color.WHITE);


               searchLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_search);
               TextView tv43= (TextView) searchLayout.findViewById(R.id.tabText);
               tv43.setText("搜索");
               tv43.setTextColor(Color.WHITE);

               locationLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_find);
               TextView tv44= (TextView) locationLayout.findViewById(R.id.tabText);
               tv44.setText("本地");
               tv44.setTextColor(Color.WHITE);

               settingLayout.findViewById(R.id.tabImg).setBackgroundResource(R.drawable.image_tabbar_button_more_down);
               TextView tv45= (TextView) settingLayout.findViewById(R.id.tabText);
               tv45.setText("设置");
               tv45.setTextColor(Color.BLUE);
               break;



       }
    }
    /*
    寻找控件
     */
    private void findView(View view) {
       homeLayout= (RelativeLayout) view.findViewById(R.id.homelayout);
        searchLayout= (RelativeLayout) view.findViewById(R.id.searchlayout);
        selectLayout= (RelativeLayout) view.findViewById(R.id.selectlayout);
        locationLayout= (RelativeLayout) view.findViewById(R.id.locationlayout);
        settingLayout= (RelativeLayout) view.findViewById(R.id.settinglayout);
    }
}

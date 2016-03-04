package com.youle.allutils.activity;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.youle.allutils.R;
import com.youle.allutils.application.LocalApplication;
import com.youle.allutils.fragment.AskFragment;
import com.youle.allutils.fragment.FindFragment;
import com.youle.allutils.fragment.MyFragment;
import com.youle.allutils.fragment.NewsFragment;
import com.youle.allutils.fragment.PriceFragment;
import com.youle.allutils.view.ToastMaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//主界面
public class MainActivity extends BaseActivity {
	private static final String TAG = "MainActivity";

	// 资讯模块
	@ViewInject(R.id.car_llyt_news)
	LinearLayout car_llyt_news;
	@ViewInject(R.id.car_iv_news)
	ImageView car_iv_news;
	@ViewInject(R.id.car_tv_news)
	TextView car_tv_news;

	// 找车模块
	@ViewInject(R.id.car_llyt_find)
	LinearLayout car_llyt_find;
	@ViewInject(R.id.car_iv_find)
	ImageView car_iv_find;
	@ViewInject(R.id.car_tv_find)
	TextView car_tv_find;

	// 降价模块
//	@ViewInject(R.id.car_llyt_price)
//	LinearLayout car_llyt_price;
//	@ViewInject(R.id.car_iv_price)
//	ImageView car_iv_price;
//	@ViewInject(R.id.car_tv_price)
//	TextView car_tv_price;

	// 问答模块
//	@ViewInject(R.id.car_llyt_ask)
//	LinearLayout car_llyt_ask;
//	@ViewInject(R.id.car_iv_ask)
//	ImageView car_iv_ask;
//	@ViewInject(R.id.car_tv_ask)
//	TextView car_tv_ask;

	// 我的模块
	@ViewInject(R.id.car_llyt_my)
	LinearLayout car_llyt_my;
	@ViewInject(R.id.car_iv_my)
	ImageView car_iv_my;
	@ViewInject(R.id.car_tv_my)
	TextView car_tv_my;

	// fragment事务
	private FragmentTransaction ft;

	// tab选中的所以
	private int chooseIndex = -1;

	// 点击退出时记录时间
	private long firstTime = 0;
	
	
	//是否发生了回收
	private boolean isRecycle=false;

	@Override
	protected void initParams() {

	}

	@Override
	protected int getLayoutID() {
		return R.layout.activity_main;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		//防止旋转 导致activity重建
		if (arg0 == null) {
			myViewOnClick(car_llyt_news);
		}
	}

	// 控件点击事件
	@OnClick({ R.id.car_llyt_news, R.id.car_llyt_find
			, R.id.car_llyt_my })
	public void myViewOnClick(View view) {
		ft = getSupportFragmentManager().beginTransaction();
		switch (view.getId()) {
		case R.id.car_llyt_news:
			if (chooseIndex != 0) {
				chooseIndex = 0;
				myTabChange(chooseIndex);
				ft.replace(R.id.car_flyt_content, NewsFragment.instantiate(
						MainActivity.this, NewsFragment.class.getName(), null),
						"news");
			}

			break;
		case R.id.car_llyt_find:
			if (chooseIndex != 1) {
				chooseIndex = 1;
				myTabChange(chooseIndex);
				ft.replace(R.id.car_flyt_content, FindFragment.instantiate(
						MainActivity.this, FindFragment.class.getName(), null),
						"find");
			}

			break;
//		case R.id.car_llyt_price:
//			if (chooseIndex != 2) {
//				chooseIndex = 2;
//				myTabChange(chooseIndex);
//				ft.replace(R.id.car_flyt_content,
//						PriceFragment.instantiate(MainActivity.this,
//								PriceFragment.class.getName(), null), "price");
//			}
//
//			break;
//		case R.id.car_llyt_ask:
//			if (chooseIndex != 3) {
//				chooseIndex = 3;
//				myTabChange(chooseIndex);
//				ft.replace(R.id.car_flyt_content, AskFragment.instantiate(
//						MainActivity.this, AskFragment.class.getName(), null),
//						"ask");
//			}
//
//			break;
		case R.id.car_llyt_my:
			if (chooseIndex != 4) {
				chooseIndex = 4;
				myTabChange(chooseIndex);
				ft.replace(R.id.car_flyt_content, MyFragment.instantiate(
						MainActivity.this, MyFragment.class.getName(), null),
						"my");
			}

			break;
		default:
			break;
		}
		ft.commit();
	}

	private void myTabChange(int chooseIndex) {
		switch (chooseIndex) {
		case 0:
			car_tv_news.setTextColor(getResources().getColor(R.color.tab_select_txt));
			car_iv_news.setImageResource(R.drawable.zixun_select);
			//car_tv_ask.setTextColor(Color.BLACK);
			car_tv_my.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_my.setImageResource(R.drawable.me);
			//car_tv_price.setTextColor(Color.BLACK);
			car_tv_find.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_find.setImageResource(R.drawable.zhaoche);
			break;
		case 1:
			car_tv_news.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_news.setImageResource(R.drawable.zixun);
			//car_tv_ask.setTextColor(Color.BLACK);
			car_tv_my.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_my.setImageResource(R.drawable.me);
			//car_tv_price.setTextColor(Color.BLACK);
			car_tv_find.setTextColor(getResources().getColor(R.color.tab_select_txt));
			car_iv_find.setImageResource(R.drawable.zhaoche_select);
			break;
		case 2:
			car_tv_news.setTextColor(Color.BLACK);
		//	car_tv_ask.setTextColor(Color.BLACK);
			car_tv_my.setTextColor(Color.BLACK);
			//car_tv_price.setTextColor(Color.BLUE);
			car_tv_find.setTextColor(Color.BLACK);
			break;
		case 3:
			car_tv_news.setTextColor(Color.BLACK);
			//car_tv_ask.setTextColor(Color.BLUE);
			car_tv_my.setTextColor(Color.BLACK);
			//car_tv_price.setTextColor(Color.BLACK);
			car_tv_find.setTextColor(Color.BLACK);
			break;
		case 4:
			car_tv_news.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_news.setImageResource(R.drawable.zhaoche);
			//car_tv_ask.setTextColor(Color.BLACK);
			car_tv_my.setTextColor(getResources().getColor(R.color.tab_select_txt));
			car_iv_my.setImageResource(R.drawable.me_select);
			//car_tv_price.setTextColor(Color.BLACK);
			car_tv_find.setTextColor(getResources().getColor(R.color.tab_default_txt));
			car_iv_find.setImageResource(R.drawable.zhaoche);
			break;

		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		long secondTime = System.currentTimeMillis();
		// 如果两次按键时间大于1000ms 则不退出
		if (secondTime - firstTime > 1000) {

			//弹出Toast
			ToastMaker.showShortToast("再按一次退出！");
			firstTime=secondTime;//更新firstTime
		} else {
			finish();
		}

	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("isRecycle", true);
		outState.putInt("chooseIndex", chooseIndex);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		isRecycle=savedInstanceState.getBoolean("isRecycle");
		chooseIndex=savedInstanceState.getInt("chooseIndex");
	}
	@Override
	protected void onResume() {
		super.onResume();
		if (isRecycle) {
			myTabChange(chooseIndex);
		}
	}
	
	public static void startActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		
		context.startActivity(intent);

	}
	
	
	
	
	
	
	private FragmentOnTouchListener fragmentOnTouchListener;
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (fragmentOnTouchListener!=null) {
			fragmentOnTouchListener.onTouch(ev);
		}
		return super.dispatchTouchEvent(ev);
	}
	public interface FragmentOnTouchListener{
		public boolean onTouch(MotionEvent ev);	
		
	}
	public void registerFragmentOnTouchListener(FragmentOnTouchListener fragmentOnTouchListener) {
		this.fragmentOnTouchListener=fragmentOnTouchListener;
		
	}
	public void unregisterFragmentOnTouchListener(FragmentOnTouchListener fragmentOnTouchListener) {
		this.fragmentOnTouchListener=null;
		
	}
}

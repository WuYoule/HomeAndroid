package com.youle.allutils.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.youle.allutils.R;

//资讯

public class NewsFragment extends BaseFragment {

	// 要闻
	@ViewInject(R.id.news_tv_important)
	private TextView new_tv_important;

	// 导购
	@ViewInject(R.id.news_tv_buy)
	private TextView new_tv_buy;

	// 试车
	@ViewInject(R.id.news_tv_try)
	private TextView new_tv_try;

	// 新车
	@ViewInject(R.id.news_tv_newcar)
	private TextView new_tv_newcar;

	// 行业
	@ViewInject(R.id.news_tv_market)
	private TextView new_tv_market;

	// 图解
	@ViewInject(R.id.news_tv_picture)
	private TextView new_tv_picture;

	@ViewInject(R.id.news_vp)
	private ViewPager vp;

	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	private NewsBuyFragment buyFragment;
	private NewsImportantFragment importantFragment;
	private NewsCarFragment carFragment;
	private NewsMarketFragment marketFragment;
	private NewsPictureFragment pictureFragment;
	private NewsTryFragment tryFragment;

	
	@OnClick({R.id.news_tv_buy,R.id.news_tv_important,R.id.news_tv_market,R.id.news_tv_newcar,R.id.news_tv_picture,R.id.news_tv_try})
	private void MyTvClick(View view){
		switch (view.getId()) {
		case R.id.news_tv_important:
			vp.setCurrentItem(0);
			break;
		case R.id.news_tv_newcar:
			vp.setCurrentItem(1);
			break;
		case R.id.news_tv_buy:
			vp.setCurrentItem(2);
			break;
		case R.id.news_tv_try:
			vp.setCurrentItem(3);
			break;
		case R.id.news_tv_picture:
			vp.setCurrentItem(4);
			break;
		case R.id.news_tv_market:
			vp.setCurrentItem(5);
			break;

		default:
			break;
		}
		
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.fragment_news_main;
	}

	@Override
	protected void initParams() {
		buyFragment = new NewsBuyFragment();
		importantFragment = new NewsImportantFragment();
		carFragment = new NewsCarFragment();
		marketFragment = new NewsMarketFragment();
		pictureFragment = new NewsPictureFragment();
		tryFragment = new NewsTryFragment();
		fragmentList.add(importantFragment);
		fragmentList.add(carFragment);
		fragmentList.add(buyFragment);
		
		fragmentList.add(tryFragment);
		fragmentList.add(pictureFragment);
		fragmentList.add(marketFragment);
		
		

		Log.i("****++++++", fragmentList.size() + "");

		vp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

			@Override
			public int getCount() {
				Log.i(getTag(), fragmentList.size() + "");
				return fragmentList.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return fragmentList.get(arg0);
			}
		});
		vp.setCurrentItem(0);
		vp.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	
	
	// 跟换textview颜色
	private void MyChangeTvColor(int selectedId) {
		switch (selectedId) {
		// 要闻
		case 0:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.tab_select_txt));
			new_tv_buy.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_try.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.defalut_color));
			break;
		// 新车
		case 1:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_buy.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_try.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.tab_select_txt));
			break;
		// 导购
		case 2:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_buy.setTextColor(getResources().getColor(R.color.tab_select_txt));
			new_tv_try.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.defalut_color));
			break;
		// 试车
		case 3:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_buy.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_try.setTextColor(getResources().getColor(R.color.tab_select_txt));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.defalut_color));
			break;
		// 图解
		case 4:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_buy.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_try.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.tab_select_txt));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.defalut_color));
			break;
		// 行业
		case 5:
			new_tv_important.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_buy.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_try.setTextColor(getResources().getColor(R.color.defalut_color));
			new_tv_market.setTextColor(getResources().getColor(
					R.color.tab_select_txt));
			new_tv_picture.setTextColor(getResources().getColor(
					R.color.defalut_color));
			new_tv_newcar.setTextColor(getResources().getColor(
					R.color.defalut_color));
			break;

		default:
			break;
		}
		

	}

	// viewpager视图切换监听器
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			MyChangeTvColor(arg0);
		}

	}

	@Override
	protected void initParams(View view) {
	}

}

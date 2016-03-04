package com.youle.allutils.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AutoCompleteTextView.Validator;

import com.alibaba.fastjson.JSONArray;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;
import com.youle.allutils.R;
import com.youle.allutils.entity.NewsImageItem;
import com.youle.allutils.view.photoview.PhotoView;

public class NewsPictureActivity extends BaseActivity {

	@ViewInject(R.id.car_picture_iv_back)
	ImageView car_picture_iv_back;

	@ViewInject(R.id.car_picture_tv_index)
	TextView car_picture_tv_index;

	@ViewInject(R.id.car_picture_vp)
	ViewPager car_picture_vp;

	// 获取的数据
	private List<NewsImageItem> dataList;

	@Override
	protected void initParams() {

		car_picture_tv_index.setText("1/" + dataList.size());
		// 绑定适配器
		car_picture_vp.setAdapter(new MyViewPagerAdapter());
		car_picture_vp.setOnPageChangeListener(new MyViewpagerChangeListener());
		car_picture_vp.setCurrentItem(0);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.news_picture_main;
	}

	public static void startActivity(Context context, String datas) {
		Intent intent = new Intent(context, NewsPictureActivity.class);
		intent.putExtra("datas", datas);
		context.startActivity(intent);

	}

	// 点击返回
	@OnClick(R.id.car_picture_iv_back)
	public void viewOnClick(View view) {
		finish();
	}

	@Override
	protected void onCreate(Bundle arg0) {
		dataList = JSONArray.parseArray(getIntent().getStringExtra("datas"),
				NewsImageItem.class);
		super.onCreate(arg0);

	}

	private class MyViewpagerChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			car_picture_tv_index.setText((arg0+1)+"/"+dataList.size());
		}
		
	}
	private class MyViewPagerAdapter extends PagerAdapter {

		boolean isShow = true;

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = getLayoutInflater().inflate(R.layout.news_picture_item,
					null);
			PhotoView picture_iv_item = (PhotoView) view
					.findViewById(R.id.picture_iv_item);
			
			TextView picture_tv_title=(TextView) view.findViewById(R.id.picture_tv_title);
  
			picture_tv_title.setText(dataList.get(position % dataList.size()).title);
			
			if (position==0) {
				// 使用picasso 避免图片出现oom以及图片错位
//				Picasso.with(getApplication())
//						.load(dataList.get(position % dataList.size()).url)
//						.placeholder(R.drawable.img1)
//						.into(picture_iv_item);
				
				picture_iv_item.setImageDrawable(getResources().getDrawable(R.drawable.img1));
			}
			if (position==1) {
				// 使用picasso 避免图片出现oom以及图片错位
//				Picasso.with(getApplication())
//						.load(dataList.get(position % dataList.size()).url)
//						.placeholder(R.drawable.img2)
//						.into(picture_iv_item);
				picture_iv_item.setImageDrawable(getResources().getDrawable(R.drawable.img2));
			}
			
			container.addView(view);
			
			
			return view;
		}

	}

}

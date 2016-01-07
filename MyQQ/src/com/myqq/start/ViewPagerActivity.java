package com.myqq.start;

import java.util.ArrayList;

import com.example.myqq.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPagerActivity extends Activity {

	private ViewPager mViewPager;
	private ImageView mImageView01;
	private ImageView mImageView02;
	private ImageView mImageView03;
	private ImageView mImageView04;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);

		mImageView01 = (ImageView) findViewById(R.id.iv01);
		mImageView02 = (ImageView) findViewById(R.id.iv02);
		mImageView03 = (ImageView) findViewById(R.id.iv03);
		mImageView04 = (ImageView) findViewById(R.id.iv04);
		
		mButton=(Button) findViewById(R.id.startBtn);
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(ViewPagerActivity.this,WhatsnewDoor.class);
				
				startActivity(intent);
				finish();
			}
		});

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		LayoutInflater mlayoutInflater = LayoutInflater.from(this);
		View mview1 = mlayoutInflater.inflate(
				R.layout.viewpager_activity_item1, null);
		View mview2 = mlayoutInflater.inflate(
				R.layout.viewpager_activity_item2, null);
		View mview3 = mlayoutInflater.inflate(
				R.layout.viewpager_activity_item3, null);
		View mview4 = mlayoutInflater.inflate(
				R.layout.viewpager_activity_item4, null);

		final ArrayList<View> mArrayList = new ArrayList<View>();
		mArrayList.add(mview1);
		mArrayList.add(mview2);
		mArrayList.add(mview3);
		mArrayList.add(mview4);

		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return mArrayList.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {

				((ViewPager) container).removeView(mArrayList.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(mArrayList.get(position));
				return mArrayList.get(position);

			}
		};

		//°ó¶¨ÊÊÅäÆ÷
		mViewPager.setAdapter(mPagerAdapter);
		//»¬¶¯¼àÌý
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				ChangeBottomIv(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {
				

			}

		});

	}
    //¸Ä±ä 4¸öImageView×´Ì¬
	protected void ChangeBottomIv(int position) {
		switch (position) {
		case 0:
			mImageView01.setBackgroundResource(R.drawable.circle_gray);
			mImageView02.setBackgroundResource(R.drawable.circle_white);
			mImageView03.setBackgroundResource(R.drawable.circle_white);
			mImageView04.setBackgroundResource(R.drawable.circle_white);
			break;
		case 1:
			mImageView01.setBackgroundResource(R.drawable.circle_white);
			mImageView02.setBackgroundResource(R.drawable.circle_gray);
			mImageView03.setBackgroundResource(R.drawable.circle_white);
			mImageView04.setBackgroundResource(R.drawable.circle_white);
			break;
		case 2:
			mImageView01.setBackgroundResource(R.drawable.circle_white);
			mImageView02.setBackgroundResource(R.drawable.circle_white);
			mImageView03.setBackgroundResource(R.drawable.circle_gray);
			mImageView04.setBackgroundResource(R.drawable.circle_white);
			break;
		case 3:
			mImageView01.setBackgroundResource(R.drawable.circle_white);
			mImageView02.setBackgroundResource(R.drawable.circle_white);
			mImageView03.setBackgroundResource(R.drawable.circle_white);
			mImageView04.setBackgroundResource(R.drawable.circle_gray);
			break;

		default:
			break;
		}

	}

}

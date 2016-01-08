package com.myqq.start;

import java.util.ArrayList;

import com.example.myqq.R;
import com.myqq.Index.MyTabActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerActivity extends Activity {

	private ViewPager mViewPager;
	private ImageView mImageView01;
	private ImageView mImageView02;
	private ImageView mImageView03;
	private ImageView mImageView04;
	private Button mButton;

	// 动画相关
	ImageView mleft;
	ImageView mright;
	TextView mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);

		mImageView01 = (ImageView) findViewById(R.id.iv01);
		mImageView02 = (ImageView) findViewById(R.id.iv02);
		mImageView03 = (ImageView) findViewById(R.id.iv03);
		mImageView04 = (ImageView) findViewById(R.id.iv04);

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
		// 开启动画的按钮
		mButton = (Button) mview4.findViewById(R.id.startBtn);

		// 动画相关
		mleft = (ImageView) mview4.findViewById(R.id.imageLeft);
		mright = (ImageView) mview4.findViewById(R.id.imageRight);
		mText = (TextView) mview4.findViewById(R.id.anim_text);
		
		

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

		// 绑定适配器
		mViewPager.setAdapter(mPagerAdapter);
		// 滑动监听
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

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 动画
                startMyAnimation();
			}
		});

	}
	//动画
	protected void startMyAnimation() {
		 //移动左边
	    AnimationSet animationSet=new AnimationSet(true);
	    TranslateAnimation mytranslate=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,-1f, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
	    mytranslate.setDuration(1500);
	    animationSet.setStartOffset(800);
	    animationSet.addAnimation(mytranslate);
	    animationSet.setFillAfter(true);
	    mleft.startAnimation(animationSet);
	    
	    //移动右边
	    AnimationSet animationSet2=new AnimationSet(true);
	    TranslateAnimation mytranslate2=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,+1f, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
	    mytranslate2.setDuration(1500);
	    animationSet2.setStartOffset(800);
	    animationSet2.addAnimation(mytranslate2);
	    animationSet2.setFillAfter(true);
	    mright.startAnimation(animationSet2);
	    
	    
	    //文本动画
	    //缩放
	    AnimationSet animationSet3=new AnimationSet(true);
	    ScaleAnimation myscale=new ScaleAnimation(1f, 3f, 1f, 3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
	    myscale.setDuration(2000);
	    //渐变
	    AlphaAnimation myalpha=new AlphaAnimation(1,0);
	    myalpha.setDuration(2000);
	    animationSet3.addAnimation(myscale);
	    animationSet3.addAnimation(myalpha);
	    
	    animationSet3.setFillAfter(true);
	    mText.startAnimation(animationSet3);
	    
	    
	    //按钮动画
	    //缩放
	    AnimationSet animationSet4=new AnimationSet(true);
	    ScaleAnimation myscale4=new ScaleAnimation(1f, 0.1f, 1f, 0.1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
	    myscale4.setDuration(1000);
	    //渐变
	    AlphaAnimation myalpha4=new AlphaAnimation(1,0);
	    myalpha4.setDuration(1000);
	    
	    
	    animationSet4.addAnimation(myscale4);
	    animationSet4.addAnimation(myalpha4);
	    
	    animationSet4.setFillAfter(true);
	    mButton.startAnimation(animationSet4);
	    
	    
	    new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(ViewPagerActivity.this,MyTabActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
		
	}

	
	
	// 改变 4个ImageView状态
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

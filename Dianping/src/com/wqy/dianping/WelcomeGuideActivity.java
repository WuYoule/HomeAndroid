package com.wqy.dianping;

import java.net.NetPermission;
import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeGuideActivity extends Activity {

	@ViewInject(R.id.welcome_guide_btn)
	private Button btn;
	@ViewInject(R.id.welcome_page)
	private ViewPager pager;

	private List<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_guide);
		ViewUtils.inject(this);
		initViewPager();
	}

	@OnClick(R.id.welcome_guide_btn)
	public void click(View view) {
       startActivity(new Intent(getApplicationContext(),Test.class) );
       finish();
	}

	private void initViewPager() {
         list=new ArrayList<View>();
         ImageView iv1=new ImageView(this);
         iv1.setImageResource(R.drawable.guide_01);
         list.add(iv1);
         ImageView iv2=new ImageView(this);
         iv2.setImageResource(R.drawable.guide_02);
         list.add(iv2);
         
         ImageView iv3=new ImageView(this);
         iv3.setImageResource(R.drawable.guide_03);
         list.add(iv3);
         
         
         pager.setAdapter(new myPagerAdapter());
         
         pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if (arg0==2) {
					btn.setVisibility(View.VISIBLE);
				}
				else {
					btn.setVisibility(View.INVISIBLE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
         
         
         
	}
	class myPagerAdapter extends PagerAdapter{
		@Override
		public int getCount() {
			
			return list.size();
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		
		
			container.removeView(list.get(position));
		}
		
		
	}

}

package com.example.bdwall;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
     MybottomLayout bottomLayout;
	 ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //“≥√Ê≥ı ºªØ
        init();
    }
	private void init() {
		 vp= (ViewPager) findViewById(R.id.myViewPage);
	     vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
	     findView();
	     setOnClick();
	     
	        vp.setOnPageChangeListener(new PageChangeListener());

		
	}
	
	private class PageChangeListener implements ViewPager.OnPageChangeListener{

	    @Override
	    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	    }

	    @Override
	    public void onPageSelected(int position) {
	       bottomLayout.initPic(position);
	    }

	    @Override
	    public void onPageScrollStateChanged(int state) {

	    }
	}
	
	 private void setOnClick() {
		    Log.i("mylog",bottomLayout.myid+"");
	        MyCallBackListener my=new MyCallBackListener();
	        bottomLayout.setOnCallbackListener(my);
	    }
	 
	 
	 
	 private class MyCallBackListener implements MybottomLayout.ICallbackListener {
	        @Override
	        public void click(int id) {
	            switch (id)
	            {
	                case R.id.homelayout:
	                    vp.setCurrentItem(0);
	                    break;
	                case R.id.locationlayout:
	                    vp.setCurrentItem(3);
	                    break;
	                case R.id.searchlayout:
	                    vp.setCurrentItem(2);
	                    break;
	                case R.id.settinglayout:
	                    vp.setCurrentItem(4);
	                    break;
	                case R.id.selectlayout:
	                    vp.setCurrentItem(1);
	                    break;
	            }

	        }
	    }
	 
	 
	  private void findView() {
	        bottomLayout = (MybottomLayout) findViewById(R.id.myBottomLayout);
	    }
	
	  private class MyFragmentAdapter extends FragmentPagerAdapter {


	        public MyFragmentAdapter(android.support.v4.app.FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int position) {

	           switch (position)
	           {
	               case 0:
	                 return new HomeFragment();

	               case 1:
	                   return   new SelectFragment();

	               case 2:
	                   return   new SearchFragment();

	               case 3:
	                   return new LocationFragment();


	               case 4:
	                   return new SettingFragment();

	           }
	            return  null;
	        }

	        @Override
	        public int getCount() {
	            return 5;
	        }
	    }


    
}

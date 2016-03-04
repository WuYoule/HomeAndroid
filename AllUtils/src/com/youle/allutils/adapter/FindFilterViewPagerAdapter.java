package com.youle.allutils.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class FindFilterViewPagerAdapter extends PagerAdapter {

	
	List<View> datas;
	public FindFilterViewPagerAdapter(List<View > datas) {
		this.datas=datas;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(datas.get(position),0);
		return datas.get(position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(datas.get(position));
		
	}

}

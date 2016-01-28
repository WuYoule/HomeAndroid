package com.wqy.utils;

import com.wqy.dianping.R;

import android.R.integer;

public class MyUtils {
 //返回值
	public static final int RequestCityCode=2;
	
	public static String[] navSort={
		"美食","电影","酒店",
		"KTV","自助餐","休闲娱乐",
		"旅游","购物","都市丽人",
		"母婴","女装","美妆",
		"户外运动","生活服务","全部"};
	public static int[] navSortImages={
		R.drawable.meet,R.drawable.nike_dunk,R.drawable.owl,
		R.drawable.paint_brush_tool,R.drawable.pan,R.drawable.power_plant,
		R.drawable.ps4_controller,R.drawable.radio_4,R.drawable.rubber_duck,
		R.drawable.sharpner,R.drawable.snowman,R.drawable.space_rocket,
		R.drawable.sunglasses,R.drawable.support,R.drawable.umbrella,
	};
	
	
	public static String[] allCategroy={
		"全部分类","今日新单","餐饮美食",
		"休闲娱乐","电影","生活服务",
		"摄影写真","酒店","旅游",
		"教育培训","抽奖公益","购物",
		"都市丽人"};
	public static int[] allCategroyImages={
		R.drawable.meet,R.drawable.nike_dunk,R.drawable.owl,
		R.drawable.paint_brush_tool,R.drawable.pan,R.drawable.power_plant,
		R.drawable.ps4_controller,R.drawable.radio_4,R.drawable.rubber_duck,
		R.drawable.sharpner,R.drawable.snowman,R.drawable.space_rocket,
		R.drawable.sunglasses
	};

	
	public static long[] allCategroyNumber=new long[allCategroy.length+5];
	
	private static final String RANDOMS="123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static String getRandom(int num){
		StringBuffer sbf=new StringBuffer();
		for (int i = 0; i < num; i++) {
			int random=(int)(Math.random()*(RANDOMS.length()));
		   sbf.append(RANDOMS.charAt(random));
		}
		return sbf.toString();
		
	}
	
}

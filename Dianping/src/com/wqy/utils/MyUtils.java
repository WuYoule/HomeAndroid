package com.wqy.utils;

import com.wqy.dianping.R;

import android.R.integer;

public class MyUtils {
 //����ֵ
	public static final int RequestCityCode=2;
	
	public static String[] navSort={
		"��ʳ","��Ӱ","�Ƶ�",
		"KTV","������","��������",
		"����","����","��������",
		"ĸӤ","Ůװ","��ױ",
		"�����˶�","�������","ȫ��"};
	public static int[] navSortImages={
		R.drawable.meet,R.drawable.nike_dunk,R.drawable.owl,
		R.drawable.paint_brush_tool,R.drawable.pan,R.drawable.power_plant,
		R.drawable.ps4_controller,R.drawable.radio_4,R.drawable.rubber_duck,
		R.drawable.sharpner,R.drawable.snowman,R.drawable.space_rocket,
		R.drawable.sunglasses,R.drawable.support,R.drawable.umbrella,
	};
	
	
	public static String[] allCategroy={
		"ȫ������","�����µ�","������ʳ",
		"��������","��Ӱ","�������",
		"��Ӱд��","�Ƶ�","����",
		"������ѵ","�齱����","����",
		"��������"};
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

package com.wqy.dianping.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;


//标记的读取
public class SharedUtils {
    private static final String FILE_NAME="dianping";
    private static final String MODE_NAME="welcome";
    private static final String CITY_INFO="cityName";
   //读取一个boolean类型的值
    public static  boolean getWelcomeBoolean(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getBoolean(MODE_NAME, false);
		
	}
    
    
    //读取一个String类型的值
    public static  String getCityName(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getString(CITY_INFO, "");
		
	}
    
  
    //写入一个boolean类型的值
    public static void  putWelcomeBoolean(Context context,boolean isFirst) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
	}
    
  //写入一个String类型的值
    public static void  putCityName(Context context,String cityName) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putString(CITY_INFO,cityName);
		editor.commit();
	}
    
    
    
}

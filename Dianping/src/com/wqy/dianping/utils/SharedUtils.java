package com.wqy.dianping.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;


//��ǵĶ�ȡ
public class SharedUtils {
    private static final String FILE_NAME="dianping";
    private static final String MODE_NAME="welcome";
    private static final String CITY_INFO="cityName";
    private static final String LOGIN_INFO="loginName";
   //��ȡһ��boolean���͵�ֵ
    public static  boolean getWelcomeBoolean(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getBoolean(MODE_NAME, false);
		
	}
    
    
    //��ȡһ��String���͵�ֵ
    public static  String getCityName(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getString(CITY_INFO, "");
		
	}
    
    
    //��ȡһ��String���͵ĵ�½����
    public static  String getloginName(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getString(LOGIN_INFO, "");
		
	}
    
  
    //д��һ��boolean���͵�ֵ
    public static void  putWelcomeBoolean(Context context,boolean isFirst) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
	}
    
  //д��һ��String���͵�ֵ
    public static void  putCityName(Context context,String cityName) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putString(CITY_INFO,cityName);
		editor.commit();
	}
    
    //д��һ��String���͵� ��½����
    public static void  putLoginName(Context context,String loginName) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putString(LOGIN_INFO,loginName);
		editor.commit();
	}
    
    
    
}

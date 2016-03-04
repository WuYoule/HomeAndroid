package com.youle.allutils.util;

import com.youle.allutils.application.LocalApplication;

import android.content.SharedPreferences;

public class AppPreferences {

	public static class PreferenceKey{
		public static final String START_VERSION="start_version";
	}
	private static AppPreferences appPreferences;
	private SharedPreferences preferences;
	private AppPreferences(){
		preferences=LocalApplication.getInstance().sharedPreferences;
		
	}
	public static AppPreferences instance(){
		if (appPreferences==null) {
			appPreferences=new AppPreferences();
			
		}
		return appPreferences;
		
	}
	public void remove(String key) {
		preferences.edit().remove(key).commit();
	}
	public String  getString(String key) {
		return preferences.getString(key, "");
	}
	public void putString(String key,String value) {
	        preferences.edit().putString(key, value);
		
	}
}

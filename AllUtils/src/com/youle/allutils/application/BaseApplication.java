package com.youle.allutils.application;

import com.youle.allutils.exception.BaseExceptionHandler;
import com.youle.allutils.exception.LocalFileHandler;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.BaseExpandableListAdapter;

public abstract class BaseApplication extends Application {
	public static final String TAG="Application";
	public static Context applicationContext;
	public SharedPreferences sharedPreferences;
	
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext=getApplicationContext();
		if (getDefaultUncaughtExceptionHandler()==null) {
			Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
		}
		else {
			Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
		}
		//≥ı ºªØ
		sharedPreferences=getSharedPreferences("local_kv", MODE_PRIVATE);
	}
	public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();

}

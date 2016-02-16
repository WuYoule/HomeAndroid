package com.youle.allutils.application;

import java.io.File;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.youle.allutils.exception.BaseExceptionHandler;
import com.youle.allutils.exception.LocalFileHandler;
import com.youle.allutils.util.JFileKit;

public class LocalApplication extends BaseApplication {
	private static final String TAG = "LocalApplication";

	private static LocalApplication instance;
	 public DbUtils dbUtils=null;
	    public HttpUtils httpUtils=null;
	    //当前屏幕的宽高
	    public int screenW=0;
	    public int screenH=0;


	public static LocalApplication Initialize() {
		if (instance == null) {
			instance = new LocalApplication();
		}
		return instance;
	}

	public static LocalApplication getInstance() {
	
		if (instance == null) {

			instance = new LocalApplication();
		}
		return instance;
	}



	@Override
	public void onCreate() {
		
		super.onCreate();
		//初始化数据库
    	//dbUtils=DbUtils.create();
    	
    	//初始化网络
    	httpUtils=new HttpUtils();
    	
		File appFolder = new File(JFileKit.getDiskCacheDir(this) + "/log");
		if (!appFolder.exists()) {
			appFolder.mkdir();
		}
		instance = this;
		//获取屏幕宽高
    	DisplayMetrics dm=getResources().getDisplayMetrics();
    	screenH=dm.heightPixels;
    	screenW=dm.widthPixels;
	}

	@Override
	public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
		return new LocalFileHandler(applicationContext);
	}

}

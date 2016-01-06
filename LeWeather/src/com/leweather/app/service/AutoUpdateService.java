package com.leweather.app.service;

import com.leweather.app.receiver.AutoUpdateReceiver;
import com.leweather.app.util.HttpCallbackListener;
import com.leweather.app.util.HttpUtil;
import com.leweather.app.util.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class AutoUpdateService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				updateWeather();
				
			}

			
		}).start();
		
		AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour=8*60*60;
		long triggerAtTime=SystemClock.elapsedRealtime()+anHour;
		Intent i=new Intent(this,AutoUpdateReceiver.class);
		PendingIntent pi=PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		return super.onStartCommand(intent, flags, startId);
		
		
	}
	//更新天气信息
	private void updateWeather() {
		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
		String city_name=preferences.getString("city_name", "");
		String address="http://api.map.baidu.com/telematics/v3/weather?location="+city_name+"&output=json&ak=B5dc31c34fcf5591939ded8c03db10ee";
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				Utility.handleWeatherResponse(AutoUpdateService.this, response);
				
			}
			
			@Override
			public void onError(Exception e) {
			
				
			}
		});
		
	}
}

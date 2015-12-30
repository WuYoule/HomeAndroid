package com.example.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class Myservice extends Service {

	private Myservice ms=this;
	private DownloadBinder mBinder=new DownloadBinder();
	class DownloadBinder extends Binder{
		
		public Myservice getService(){
			
			return ms;
		}
		
		
		public void startDownload() {
			Log.i("Myservice", "startDownload()");
		}
		
		public int getProcess() {
			Log.i("Myservice", "getProcess()");
			return 0;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("Myservice", "onBind()");
		return mBinder;
	}

	public void HelloWorld(){
		Log.i("Myservice", "HelloWorld()");
	}
	@Override
	public void onCreate() {
	
		super.onCreate();
		Notification notification=new Notification(R.drawable.ic_launcher, "Notification comes", System.currentTimeMillis());
		Intent intent=new Intent(this, MainActivity.class);
		PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "This is Title", "hello world!", pendingIntent);
		startForeground(1, notification);
		Log.i("Myservice", "onCreate()");
		
	}

	@Override
	public void onDestroy() {
		Log.i("Myservice", "onDestory()");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Myservice", "onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

}

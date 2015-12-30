package com.example.servicebestpractice;

import java.util.Date;




import android.R.integer;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.util.Log;

public class LongRunningService extends Service {

	
	@Override
	public void onCreate() {
		 Log.i("LongRunningService", "onCreate()");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
   private int i=0;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 Log.i("LongRunningService", "onStartCommand()");
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//			//	Log.i("LongRunningService", new Date().toString());
//				
//
//			}
//		}
//
//		).start();
		 Notification notification=new Notification(R.drawable.ic_launcher, "Notification comes", System.currentTimeMillis());
			Intent i2=new Intent(this, MainActivity.class);
			PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, i2, 0);
			notification.setLatestEventInfo(this, "This is Title"+i, "hello world!"+i, pendingIntent);
			startForeground(1, notification);
		i++;
		
		AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour=10*1000;
		long triggerAtTime=SystemClock.elapsedRealtime()+anHour;
		Intent i=new Intent(this,AlarmReceiver.class);
		PendingIntent pi=PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		return super.onStartCommand(intent, flags, startId);
	}

}

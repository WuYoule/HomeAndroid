package com.wqy.dianping;

import java.util.Timer;
import java.util.TimerTask;

import com.wqy.dianping.utils.SharedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//延时跳转可以使用Handler    也可以使用java中定时器
public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
//		new Handler(new Handler.Callback() {
//			// 处理接收到消息的方法
//			@Override
//			public boolean handleMessage(Message msg) {
//				startActivity(new Intent(getApplicationContext(), Test.class));
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 3000);// 延时3秒进行任务执行
		
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (SharedUtils.getWelcomeBoolean(getApplicationContext())) {
					startActivity(new Intent(getApplicationContext(), MainActivity.class));
				}
				else {
				
					startActivity(new Intent(getApplicationContext(), WelcomeGuideActivity.class));
				   //保存访问记录
					SharedUtils.putWelcomeBoolean(getApplicationContext(), true);
				}
				finish();
				
				
				
			}
		}, 3000);
	}
}

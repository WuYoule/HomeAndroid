package com.wqy.dianping;

import java.util.Timer;
import java.util.TimerTask;

import com.wqy.dianping.utils.SharedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//��ʱ��ת����ʹ��Handler    Ҳ����ʹ��java�ж�ʱ��
public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
//		new Handler(new Handler.Callback() {
//			// ������յ���Ϣ�ķ���
//			@Override
//			public boolean handleMessage(Message msg) {
//				startActivity(new Intent(getApplicationContext(), Test.class));
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 3000);// ��ʱ3���������ִ��
		
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (SharedUtils.getWelcomeBoolean(getApplicationContext())) {
					startActivity(new Intent(getApplicationContext(), MainActivity.class));
				}
				else {
				
					startActivity(new Intent(getApplicationContext(), WelcomeGuideActivity.class));
				   //������ʼ�¼
					SharedUtils.putWelcomeBoolean(getApplicationContext(), true);
				}
				finish();
				
				
				
			}
		}, 3000);
	}
}

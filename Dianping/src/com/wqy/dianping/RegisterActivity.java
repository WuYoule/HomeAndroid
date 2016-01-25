package com.wqy.dianping;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class RegisterActivity extends Activity implements OnClickListener {
	
	@ViewInject(R.id.register_passnum)
	private Button register_passnum;
	@ViewInject(R.id.register_back)
	private ImageView register_back;
	private MyCountTimer countTimer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_register_act);
		ViewUtils.inject(this);
		countTimer=new MyCountTimer(60000, 1000);
		register_back.setOnClickListener(this);
		register_passnum.setOnClickListener(this);
	
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_passnum:
			//开启倒计时
			countTimer.start();
			break;
		case R.id.register_back:
			finish();
		default:
			break;
		}
		
	}
	class MyCountTimer extends CountDownTimer{

		/**
		 * 
		 * @param millisInFuture 时间间隔
		 * @param countDownInterval
		 */
		public MyCountTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		  //间隔时间内调用的方法
		@Override
		public void onTick(long millisUntilFinished) {
			register_passnum.setText(millisUntilFinished/1000+"s后重新发送");
			register_passnum.setBackgroundResource(R.color.d4d4d4);
			register_passnum.setClickable(false);
			
		}
		//间隔时间结束的时候调用的方法
     
		@Override
		public void onFinish() {
			//更新组件
			register_passnum.setText(R.string.getnumStr);
			register_passnum.setBackgroundResource(R.color.ebebeb);
			register_passnum.setClickable(true);
			
		}
		
	}

}

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
			//��������ʱ
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
		 * @param millisInFuture ʱ����
		 * @param countDownInterval
		 */
		public MyCountTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		  //���ʱ���ڵ��õķ���
		@Override
		public void onTick(long millisUntilFinished) {
			register_passnum.setText(millisUntilFinished/1000+"s�����·���");
			register_passnum.setBackgroundResource(R.color.d4d4d4);
			register_passnum.setClickable(false);
			
		}
		//���ʱ�������ʱ����õķ���
     
		@Override
		public void onFinish() {
			//�������
			register_passnum.setText(R.string.getnumStr);
			register_passnum.setBackgroundResource(R.color.ebebeb);
			register_passnum.setClickable(true);
			
		}
		
	}

}

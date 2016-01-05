package com.leweather.app.activity;


import com.leweather.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity implements OnClickListener{

	
	private LinearLayout weatherInfoLinearLayout;
	
	//��ʾ������
	private TextView cityNameText;
	
	//��ʾ����ʱ��
	private TextView publishText;
	
	//��ʾ��������
	private TextView weatherDescText;
	
	//��ʾ����
	private TextView tempText;
	
	//��ʾ��ǰ����
	private  TextView currentDateText;
	
	//�л����а�ť
	private Button  switchCityButton;
	
	//����������ť
	private Button refreshButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		
		//��ʼ���ؼ�
		
		weatherInfoLinearLayout=(LinearLayout) findViewById(R.id.wether_info_layout);
		
		cityNameText=(TextView) findViewById(R.id.city_name);
		
		publishText=(TextView) findViewById(R.id.publish_text);
		
		weatherDescText=(TextView) findViewById(R.id.current_desp);
		
		currentDateText=(TextView) findViewById(R.id.current_date);
		
		tempText=(TextView) findViewById(R.id.temp);
		
		switchCityButton=(Button) findViewById(R.id.switchbtn);
		
		refreshButton=(Button) findViewById(R.id.refreshbtn);
        //Intent �����		
		String cn=getIntent().getStringExtra("city_name");
		
		if (!TextUtils.isEmpty(cn)) {
			//�г������� ��ȥ��ѯ����
			publishText.setText("ͬ����...");
			weatherInfoLinearLayout.setVisibility(View.INVISIBLE);
			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherCode(cn);
			
			
		}else{
			//û�г�������ʱֱ����ʾ��������
			showWeather();
			
			
			
		}
		
		switchCityButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private void showWeather() {
		// TODO Auto-generated method stub
		
	}











	private void queryWeatherCode(String cn) {
		// TODO Auto-generated method stub
		
	}











	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}

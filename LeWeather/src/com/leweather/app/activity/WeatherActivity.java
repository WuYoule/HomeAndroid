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
	
	//显示城市名
	private TextView cityNameText;
	
	//显示发布时间
	private TextView publishText;
	
	//显示天气描述
	private TextView weatherDescText;
	
	//显示气温
	private TextView tempText;
	
	//显示当前日期
	private  TextView currentDateText;
	
	//切换城市按钮
	private Button  switchCityButton;
	
	//跟新天气按钮
	private Button refreshButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		
		//初始化控件
		
		weatherInfoLinearLayout=(LinearLayout) findViewById(R.id.wether_info_layout);
		
		cityNameText=(TextView) findViewById(R.id.city_name);
		
		publishText=(TextView) findViewById(R.id.publish_text);
		
		weatherDescText=(TextView) findViewById(R.id.current_desp);
		
		currentDateText=(TextView) findViewById(R.id.current_date);
		
		tempText=(TextView) findViewById(R.id.temp);
		
		switchCityButton=(Button) findViewById(R.id.switchbtn);
		
		refreshButton=(Button) findViewById(R.id.refreshbtn);
        //Intent 传入的		
		String cn=getIntent().getStringExtra("city_name");
		
		if (!TextUtils.isEmpty(cn)) {
			//有城市名是 就去查询天气
			publishText.setText("同步中...");
			weatherInfoLinearLayout.setVisibility(View.INVISIBLE);
			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherCode(cn);
			
			
		}else{
			//没有城市名称时直接显示本地天气
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

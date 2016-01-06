package com.leweather.app.activity;

import com.leweather.app.R;
import com.leweather.app.service.AutoUpdateService;
import com.leweather.app.util.HttpCallbackListener;
import com.leweather.app.util.HttpUtil;
import com.leweather.app.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity implements OnClickListener {

	private LinearLayout weatherInfoLinearLayout;

	// 显示城市名
	private TextView cityNameText;

	// 显示发布时间
	private TextView publishText;

	// 显示天气描述
	private TextView weatherDescText;

	// 显示气温
	private TextView tempText;

	// 显示当前日期
	private TextView currentDateText;

	// 切换城市按钮
	private Button switchCityButton;

	// 跟新天气按钮
	private Button refreshButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);

		// 初始化控件

		weatherInfoLinearLayout = (LinearLayout) findViewById(R.id.wether_info_layout);

		cityNameText = (TextView) findViewById(R.id.city_name);

		publishText = (TextView) findViewById(R.id.publish_text);

		weatherDescText = (TextView) findViewById(R.id.current_desp);

		currentDateText = (TextView) findViewById(R.id.current_date);

		tempText = (TextView) findViewById(R.id.temp);

		switchCityButton = (Button) findViewById(R.id.switchbtn);

		refreshButton = (Button) findViewById(R.id.refreshbtn);
		// Intent 传入的
		String cn = getIntent().getStringExtra("city_name");
	//	Log.i("city_name", cn);

		if (!TextUtils.isEmpty(cn)) {
			// 有城市名是 就去查询天气
			publishText.setText("同步中...");
			weatherInfoLinearLayout.setVisibility(View.INVISIBLE);
			cityNameText.setVisibility(View.INVISIBLE);
			queryWeatherInfo(cn);

		} else {
			// 没有城市名称时直接显示本地天气
			showWeather();

		}

		switchCityButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);

	}

	

	private void queryWeatherInfo(String cityname) {
        String address="http://api.map.baidu.com/telematics/v3/weather?location="+cityname+"&output=json&ak=B5dc31c34fcf5591939ded8c03db10ee";
        queryFromServer(address, "weatherCode"); 
	}

	private void queryFromServer(String address, final String type) {
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				if ("weatherCode".equals(type)) {
					//处理服务器返回的天气信息
					Utility.handleWeatherResponse(WeatherActivity.this, response);
					runOnUiThread(new  Runnable() {
						public void run() {
							showWeather();
						}
					});
				}
				
			}
			
			@Override
			public void onError(Exception e) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						publishText.setText("同步失败");
						
					}
				});
				
			}
		});
		
	}
	
	private void showWeather() {
            SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
            cityNameText.setText(preferences.getString("city_name", ""));
            tempText.setText(preferences.getString("temp", ""));
            weatherDescText.setText(preferences.getString("desc", ""));
            currentDateText.setText(preferences.getString("time", ""));
            
            publishText.setText("同步完毕");
            weatherInfoLinearLayout.setVisibility(View.VISIBLE);
            cityNameText.setVisibility(View.VISIBLE);
            
            
            //开启自动更新服务
            Intent intent=new Intent(this,AutoUpdateService.class);
            startService(intent);
            
            
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.switchbtn:
			Intent intent = new Intent(this, ChooseAreaActivity.class);
			intent.putExtra("from_weather_activity", true);
			startActivity(intent);
			finish();
			break;

		case R.id.refreshbtn:
			publishText.setText("同步中...");
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(this);
			String cityname = preferences.getString("city_name", "");
			Log.i("cityname", cityname);
			if (!TextUtils.isEmpty(cityname)) {
				queryWeatherInfo(cityname);
			}
		default:
			break;
		}

	}

}

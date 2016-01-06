package com.leweather.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.TunnelRefusedException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Parser;

import android.R.integer;
import android.R.string;
import android.app.job.JobInfo;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;

import com.leweather.app.model.City;
import com.leweather.app.model.County;
import com.leweather.app.model.LeWeatherDB;
import com.leweather.app.model.Province;

public class Utility {
	// 解析和处理服务器返回的省级数据(xml)
	public synchronized static boolean handleProvincesResponse(
			LeWeatherDB leWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceName(array[0]);
					province.setProvinceCode(array[1]);

					// 将解析出来的数据存储到Province表中
					leWeatherDB.saveProvince(province);

				}
				return true;
			}
		}
		return false;

	}

	// 解析和处理服务器返回的省级数据(xml)
	public synchronized static boolean handleCitiesResponse(
			LeWeatherDB leWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String p : allCities) {
					String[] array = p.split("\\|");
					City city = new City();
					city.setCityName(array[0]);
					city.setCityCode(array[1]);
					city.setProvinceId(provinceId);
					// 将解析出来的数据存储到Province表中
					leWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;

	}

	// 解析和处理服务器返回的县级数据(xml)
	public synchronized static boolean handleCountiesResponse(
			LeWeatherDB leWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String p : allCounties) {
					String[] array = p.split("\\|");
					County county = new County();
					county.setCountyName(array[0]);
					county.setCountyCode(array[1]);
					county.setCityId(cityId);
					// 将解析出来的数据存储到Province表中
					leWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;

	}

	// 解析和处理服务器返回的实际数据(json)
	public synchronized static boolean handleCitiesResponseJson(
			LeWeatherDB leWeatherDB, String response, int provinceId,
			int childCount) {

		if (childCount > 0) {
			Log.i("childCount", childCount + "");
			if (!TextUtils.isEmpty(response)) {
				response = response.substring(4, response.length() - 1);
				Log.i("CITY", response);
				try {
					JSONObject jsonObject = new JSONObject(response);
					String listStr = jsonObject.getString("list");
					JSONObject jsonObject2 = new JSONObject(listStr);
					for (int i = 1; i <= childCount; i++) {
						String listStr2 = jsonObject2.getString("wjr" + i);
						JSONObject jsonObject3 = new JSONObject(listStr2);

						City city = new City();
						city.setProvinceId(provinceId);
						city.setCityName(jsonObject3.getString("diming"));
						city.setCityCode(jsonObject3.getString("daima"));

						city.setChildCount(Integer.parseInt(jsonObject3
								.getString("zidi")));


						// 将解析出来的数据存储到Province表中
						leWeatherDB.saveCity(city);
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

			}
		}
		return false;

	}

	// 解析和处理服务器返回的省级数据(json)
	public synchronized static boolean handleProvincesResponseJson(
			LeWeatherDB leWeatherDB, String response) {

		if (!TextUtils.isEmpty(response)) {
			response = response.substring(4, response.length() - 1);

			try {

				JSONObject jsonObject = new JSONObject(response);
				String listStr = jsonObject.getString("list");
				int count = Integer.parseInt(jsonObject.getString("zidi"));
				JSONObject jsonObject2 = new JSONObject(listStr);
				for (int i = 1; i <= count; i++) {
					String listStr2 = jsonObject2.getString("wjr" + i);
					JSONObject jsonObject3 = new JSONObject(listStr2);

					Province province = new Province();
					province.setProvinceName(jsonObject3.getString("diming"));
					province.setProvinceCode(jsonObject3.getString("daima"));

					province.setChildCount(Integer.parseInt(jsonObject3
							.getString("zidi")));

					// 将解析出来的数据存储到Province表中
					leWeatherDB.saveProvince(province);

				}

				return true;

			} catch (JSONException e) {

				e.printStackTrace();
				return false;
			}

		}
		return false;

	}

	// 解析 天气的json数据 并保存到本地
	public static void handleWeatherResponse(Context context, String response) {

		
		try {
			
			Log.i("Weather", response);
			JSONObject jsonObject = new JSONObject(response);
			
			String results=jsonObject.getString("results");
			
			JSONArray jsonArrayResults=new JSONArray(results);
			JSONObject jsonObjectResults=(JSONObject) jsonArrayResults.get(0);
			
			String cityName=jsonObjectResults.getString("currentCity");
			
			String pm25=jsonObjectResults.getString("pm25");
			
			String weather_data=jsonObjectResults.getString("weather_data");
			
			
			JSONArray jsonArrayWeatherData=new JSONArray(weather_data);
			JSONObject jsonObjectWeatherData=(JSONObject) jsonArrayWeatherData.get(0);
			
			
			
			
		   
		   String temp=jsonObjectWeatherData.getString("temperature");
		   String desc=jsonObjectWeatherData.getString("weather")+" "+jsonObjectWeatherData.getString("wind");
		   String time=jsonObjectWeatherData.getString("date");
		   
		   
		 Log.i("Youle results",cityName+" "+pm25+" "+temp+" "+desc+" "+time);
		   saveWeatherInfo(context,cityName,pm25,temp,desc,time);
		   
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

	}

	//将天气数据存储到SharedPreferendes文件
	private static void saveWeatherInfo(Context context, String cityName,
			String pm25, String temp, String desc, String time) {
		
	    SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(context).edit();
	    
	    editor.putBoolean("city_selected", true);
	    editor.putString("city_name", cityName);
	    editor.putString("pm25", pm25);
	    editor.putString("temp", temp);
	    editor.putString("desc", desc);
	    editor.putString("time", time);
	    editor.commit();
	    
		
	}
}

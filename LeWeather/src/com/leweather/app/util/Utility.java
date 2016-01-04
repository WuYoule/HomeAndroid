package com.leweather.app.util;

import android.text.TextUtils;

import com.leweather.app.model.City;
import com.leweather.app.model.County;
import com.leweather.app.model.LeWeatherDB;
import com.leweather.app.model.Province;

public class Utility {
    //解析和处理服务器返回的省级数据
	public synchronized static boolean handleProvincesResponse(LeWeatherDB leWeatherDB,String response)
	{
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces=response.split(",");
			if (allProvinces!=null&&allProvinces.length>0) {
				 for(String p:allProvinces){
					 String[] array=p.split("\\|");
					 Province province=new  Province();
					 province.setProvinceName(array[0]);
					 province.setProvinceCode(array[1]);
					 
					 //将解析出来的数据存储到Province表中
					 leWeatherDB.saveProvince(province);
					 
				 }
				 return true;
			}
		}
		return false;
		
	}
	
	
	
	//解析和处理服务器返回的省级数据
		public synchronized static boolean handleCitiesResponse(LeWeatherDB leWeatherDB,String response,int provinceId)
		{
			if (!TextUtils.isEmpty(response)) {
				String[] allCities=response.split(",");
				if (allCities!=null&&allCities.length>0) {
					 for(String p:allCities){
						 String[] array=p.split("\\|");
						City city=new City();
						 city.setCityName(array[0]);
						 city.setCityCode(array[1]);
						 city.setProvinceId(provinceId);
						 //将解析出来的数据存储到Province表中
						 leWeatherDB.saveCity(city);
					 }
					 return true;
				}
			}
			return false;
			
		}
		
		//解析和处理服务器返回的县级数据
				public synchronized static boolean handleCountiesResponse(LeWeatherDB leWeatherDB,String response,int cityId)
				{
					if (!TextUtils.isEmpty(response)) {
						String[] allCounties=response.split(",");
						if (allCounties!=null&&allCounties.length>0) {
							 for(String p:allCounties){
								 String[] array=p.split("\\|");
								 County county=new County();
								 county.setCountyName(array[0]);
								 county.setCountyCode(array[1]);
								 county.setCityId(cityId);
								 //将解析出来的数据存储到Province表中
								 leWeatherDB.saveCounty(county);
							 }
							 return true;
						}
					}
					return false;
					
				}
}

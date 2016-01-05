package com.leweather.app.activity;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

import com.leweather.app.R;
import com.leweather.app.model.City;
import com.leweather.app.model.County;
import com.leweather.app.model.LeWeatherDB;
import com.leweather.app.model.Province;
import com.leweather.app.util.HttpCallbackListener;
import com.leweather.app.util.HttpUtil;
import com.leweather.app.util.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAreaActivity extends Activity {
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	private ProgressDialog progressDialog;

	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;

	private LeWeatherDB leWeatherDB;

	private List<String> dataList = new ArrayList<String>();

	// 省列表
	private List<Province> provinceList;
	// 市列表
	private List<City> cityList;
	// 县列表
	private List<County> countyList;

	// 选中的省
	private Province selectedProvince;
	// 选中的市
	private City selectedCity;
	// 选中的县
	private County selectedCounty;

	// 当前选中的级别
	private int currentLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		listView = (ListView) findViewById(R.id.list_view);

		titleText = (TextView) findViewById(R.id.title_text);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		leWeatherDB = LeWeatherDB.getInstance(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				if (currentLevel == LEVEL_PROVINCE) {
					if (provinceList.get(index).getChildCount() != 0) {
						selectedProvince = provinceList.get(index);
						queryCities();
					}
				} else if (currentLevel == LEVEL_CITY) {

//					selectedCity = cityList.get(index);
//					queryCounties();
					
					String cityCode=cityList.get(index).getCityCode();
					
					

				}

			}
		});
		queryProvinces();
	}

	private void queryProvinces() {
		provinceList = leWeatherDB.loadProvinces();
		if (provinceList.size() > 0) {
			dataList.clear();
			for (Province province : provinceList) {
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel = LEVEL_PROVINCE;
		} else {
			queryFromServer(null, "province");
		}

	}
	
	// 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
		private void queryCities() {
			cityList = leWeatherDB.loadCities(selectedProvince.getId());
		//	Log.i("queryCities", cityList.size() + "--" + selectedProvince.getId());
			if (cityList.size() > 0) {
				dataList.clear();
				for (City city : cityList) {
					dataList.add(city.getCityName());
				}
				adapter.notifyDataSetChanged();
				listView.setSelection(0);
				titleText.setText(selectedProvince.getProvinceName());
				currentLevel = LEVEL_CITY;
			} else {
				queryFromServer(selectedProvince.getProvinceCode(), "city");

			}
		}
		// 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
		private void queryCounties() {
			countyList = leWeatherDB.loadCounties(selectedCity.getId());
			if (countyList.size() > 0) {
				dataList.clear();
				for (County county : countyList) {
					dataList.add(county.getCountyName());
				}
				adapter.notifyDataSetChanged();
				listView.setSelection(0);
				titleText.setText(selectedCity.getCityName());
				currentLevel = LEVEL_COUNTY;
			} else {
				queryFromServer(selectedCity.getCityCode(), "county");

			}
		}

	private void queryFromServer(final String code, final String type) {
		String address;
		if (!TextUtils.isEmpty(code)) {
			address = "http://api.dangqian.com/apidiqu2/api.asp?format=json&callback=wjr&id="
					+ code;

		} else {
			address = "http://api.dangqian.com/apidiqu2/api.asp?format=json&callback=wjr&id=000000000000";
		}
		showProgressDialog();
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				boolean result = false;
				if ("province".equals(type)) {
					result = Utility.handleProvincesResponseJson(leWeatherDB,
							response);
				} else if ("city".equals(type)) {
					result = Utility.handleCitiesResponseJson(leWeatherDB,
							response, selectedProvince.getId(),
							selectedProvince.getChildCount());
				} else if ("county".equals(type)) {
					result = Utility.handleCountiesResponse(leWeatherDB,
							response, selectedCity.getId());
				}
				if (result) {
					// 通过runOnUiThread()方法回到主线程处理逻辑
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							closeProgressDialog();

							if ("province".equals(type)) {
								queryProvinces();

							} else if ("city".equals(type)) {
								queryCities();

							} else if ("county".equals(type)) {
								queryCounties();

							}
						}

					});
				} else {
					closeProgressDialog();

				}
			}

			@Override
			public void onError(Exception e) {
				// 通过runOnUiThread()方法回到主线程处理逻辑
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "加载失败",
								Toast.LENGTH_SHORT).show();
					}

				});

			}
		});

	}

	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("正在加载");
			progressDialog.setCanceledOnTouchOutside(false);

		}
		progressDialog.show();

	}

	private void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

	}

	

	

	// 捕获Back按键，根据当前的级别来判断，此时应该返回市列表、省列表、还是直接退出
	@Override
	public void onBackPressed() {

		if (currentLevel == LEVEL_COUNTY) {
			queryCities();
		} else if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			finish();
		}
	}
}

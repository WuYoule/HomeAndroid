package com.wqy.dianping.fragment;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wqy.dianping.AllCategroyActivity;
import com.wqy.dianping.CityActivity;
import com.wqy.dianping.R;
import com.wqy.dianping.utils.SharedUtils;
import com.wqy.utils.MyUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//首页
public class FragmentHome extends Fragment implements LocationListener {
	@ViewInject(R.id.index_top_city)
	private TextView topCity;

	private String cityName;// 当前城市名

	private LocationManager locationManager;

	@ViewInject(R.id.home_nav_sort)
	private GridView navSort;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_index, container, false);
		ViewUtils.inject(this, view);

		// 获取数据并且显示
		Log.i("TAG", "onCreateView");
		// Toast.makeText(getActivity(), SharedUtils.getCityName(getActivity()),
		// Toast.LENGTH_SHORT).show();
		// topCity.setText(SharedUtils.getCityName(getActivity()));
		
		navSort.setAdapter(new NavAdapter());
		return view;

	}

	@OnClick({ R.id.index_top_city })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index_top_city:// 城市
			startActivityForResult(
					new Intent(getActivity(), CityActivity.class),
					MyUtils.RequestCityCode);
			break;

		default:
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MyUtils.RequestCityCode
				&& resultCode == Activity.RESULT_OK) {
			cityName = data.getStringExtra("cityName");
			topCity.setText(cityName);
		}
	}

	@Override
	public void onStart() {

		super.onStart();
		Log.i("TAG", "onStart");
		// 检查当前的gps模块
		// checkGPSIsOpen();
	}

	// 检查是否打开gps
	private void checkGPSIsOpen() {
		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		boolean isOpen = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!isOpen) {
			// 进入GPS设置页面
			Intent intent = new Intent();
			intent.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivityForResult(intent, 0);
		}
		// 开始定位
		startLocation();

	}

	private void startLocation() {
		Log.i("TAG", "startLocation");
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 10, this);

	}

	// 接收并处理消息
	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				topCity.setText(cityName);
				break;

			default:
				break;
			}
			return false;
		}
	});

	// 位置信息更改执行的方法
	@Override
	public void onLocationChanged(Location location) {
		Log.i("TAG", "onLocationChanged");
		updateWithNewLocation(location);

	}

	// 获取位置的经纬度并且定位城市
	private void updateWithNewLocation(Location location) {
		double lat = 0.0, lng = 0.0;
		if (location != null) {
			lat = location.getLatitude();
			lng = location.getLongitude();
			Log.i("TAY", "经度：" + lat + "维度" + lng);

		} else {
			cityName = "无法获取城市信息";
		}

		List<Address> list = null;

		Geocoder geocoder = new Geocoder(getActivity());
		try {
			list = geocoder.getFromLocation(lat, lng, 2);

		} catch (IOException e) {

			e.printStackTrace();
		}

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Address address = list.get(i);
				cityName = address.getLocality();// 获取城市
			}
		}
		// 发送空消息
		handler.sendEmptyMessage(1);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("TAG", "onDestroy 1");
		// 保存城市
		SharedUtils.putCityName(getActivity(), cityName);
		Log.i("TAG", "onDestroy 2");
		// 停止定位
		// stopLocation();
		Log.i("TAG", "onDestroy 3");
	}

	// 停止定位
	private void stopLocation() {
		locationManager.removeUpdates(this);

	}

	public class NavAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MyUtils.navSort.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
			MyHolder holder;
			if (convertView==null) {
				holder=new MyHolder();
				convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_index_nav_item, null);
				ViewUtils.inject(holder, convertView);
				convertView.setTag(holder);
				
			}
			else {
				holder=(MyHolder) convertView.getTag();
			}
			holder.textView.setText(MyUtils.navSort[position]);
			holder.imageView.setImageResource(MyUtils.navSortImages[position]);
			//选中全部
			if (position==MyUtils.navSort.length-1) {
				holder.imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startActivity(new Intent(getActivity(), AllCategroyActivity.class));
						
					}
				});
			}
			return convertView;
		}

	}
	public class MyHolder{
		@ViewInject(R.id.home_nav_item_desc)
		public  TextView textView;
		@ViewInject(R.id.home_nav_item_image)
		public ImageView imageView;
	}

}

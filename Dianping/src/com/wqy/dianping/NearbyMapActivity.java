package com.wqy.dianping;





import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.mapcore.t;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * AMapV2��ͼ�н��ܶ�λ����ģʽ��ʹ�ã�������λ��׷�棬��ת
 */
public class NearbyMapActivity extends Activity implements LocationSource,
		AMapLocationListener {
	private AMap aMap;
	@ViewInject(R.id.search_mymap)
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private RadioGroup mGPSModeGroup;
	
	private TextView mLocationErrText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);// ����ʾ����ı�����
		setContentView(R.layout.search_map);
	
		ViewUtils.inject(this);
		mapView.onCreate(savedInstanceState);// �˷���������д
		init();
	}

	/**
	 * ��ʼ��
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
//		mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
//		mGPSModeGroup.setOnCheckedChangeListener(this);
////		mLocationErrText = (TextView)findViewById(R.id.location_errInfo_text);
//		mLocationErrText.setVisibility(View.GONE);
	}

	/**
	 * ����һЩamap������
	 */
	private void setUpMap() {
		aMap.setLocationSource(this);// ���ö�λ����
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// ����Ĭ�϶�λ��ť�Ƿ���ʾ
		aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
		// ���ö�λ������Ϊ��λģʽ �������ɶ�λ��������ͼ������������ת����
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
	}

//	@Override
//	public void onCheckedChanged(RadioGroup group, int checkedId) {
//		switch (checkedId) {
//		case R.id.gps_locate_button:
//			// ���ö�λ������Ϊ��λģʽ
//			aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
//			break;
//		case R.id.gps_follow_button:
//			// ���ö�λ������Ϊ ����ģʽ
//			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
//			break;
//		case R.id.gps_rotate_button:
//			// ���ö�λ������Ϊ���ݵ�ͼ��������ת
//			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
//			break;
//		}
//
//	}

	/**
	 * ����������д
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
		if(null != mlocationClient){
			mlocationClient.onDestroy();
		}
	}

	/**
	 * ��λ�ɹ���ص�����
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
//				mLocationErrText.setVisibility(View.GONE);
				mListener.onLocationChanged(amapLocation);// ��ʾϵͳС����
			} else {
				String errText = "��λʧ��," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr",errText);
//				mLocationErrText.setVisibility(View.VISIBLE);
//				mLocationErrText.setText(errText);
			}
		}
	}

	/**
	 * ���λ
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			//���ö�λ����
			mlocationClient.setLocationListener(this);
			//����Ϊ�߾��ȶ�λģʽ
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//���ö�λ����
			mlocationClient.setLocationOption(mLocationOption);
			// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
			// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����stopLocation()������ȡ����λ����
			// �ڶ�λ�������ں��ʵ��������ڵ���onDestroy()����
			// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������stopLocation()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
			mlocationClient.startLocation();
		}
	}

	/**
	 * ֹͣ��λ
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	
}


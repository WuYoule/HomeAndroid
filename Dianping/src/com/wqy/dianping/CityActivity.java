package com.wqy.dianping;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.wqy.consts.CONSTS;
import com.wqy.entity.City;
import com.wqy.entity.ResponseObject;
import com.wqy.view.SiderBar;
import com.wqy.view.SiderBar.OnTouchingLetterChangedListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CityActivity extends Activity implements OnTouchingLetterChangedListener {

	@ViewInject(R.id.city_list)
	private ListView listDatas;
	
	private List<City> cityList;
	
	@ViewInject(R.id.city_sider_bar)
	private SiderBar siderBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_city_list);
		ViewUtils.inject(this);
		
		View view=LayoutInflater.from(this).inflate(R.layout.home_city_search, null);
		listDatas.addHeaderView(view);
		//开启异步任务
		new CityDataTask().execute();
		
		siderBar.setOnTouchingLetterChangedListener(this);
		
		
	}

	@OnItemClick(R.id.city_list)
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		Intent intent=new Intent();
		TextView textview=(TextView) view.findViewById(R.id.city_list_item_name);
		intent.putExtra("cityName", textview.getText().toString());
		setResult(RESULT_OK,intent);
		finish();
		
	}
	
	@OnClick({R.id.index_city_back,R.id.index_city_flushcity})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.index_top_city://返回
			finish();
			
			break;
		case R.id.index_city_flushcity://刷新
			new CityDataTask().execute();
		default:
			break;
		}
		
	}
	
	
	//使用异步任务获取城市json
	public class CityDataTask extends AsyncTask<Void, Void, List<City>>{

		@Override
		protected List<City> doInBackground(Void... params) {
			HttpClient client=new DefaultHttpClient();
			//Log.i("Tag", CONSTS.City_Data_URI);
			HttpGet httpGet=new HttpGet(CONSTS.City_Data_URI);
			
			HttpResponse httpResponse;
			try {
				httpResponse = client.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode()==200) {
					String jsonString=EntityUtils.toString(httpResponse.getEntity());
				//	Log.i("Tag", jsonString);
					return parseCityDatasJSON(jsonString);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			return null;
		}
		@Override
		protected void onPostExecute(List<City> result) {
			
			super.onPostExecute(result);
		//	Log.i("COUNT",result.get(1).toString());
			cityList=result;
			//适配显示
			MyAadapter adapter=new  MyAadapter(cityList);
			listDatas.setAdapter(adapter);
			
		}

		private List<City> parseCityDatasJSON(String jsonString) {
		//	Log.i("Tag1111",jsonString);
			Gson gson=new Gson();
			Type listType=new TypeToken<ResponseObject<List<City>>>(){}.getType();
			ResponseObject<List<City>> citities=gson.fromJson(jsonString, listType);
			
			return citities.getDatas();
		}
		
		private StringBuffer buffer=new StringBuffer();//用来第一次保存字母的索引
		private List<String> firstList=new ArrayList<String>();//用来保存索引对应的城市
		
		
		public class MyAadapter extends BaseAdapter{
			private List<City> listCityDatas;
			public MyAadapter(List<City> listCityDatas){
				this.listCityDatas=listCityDatas;
			}

			@Override
			public int getCount() {
				
				return listCityDatas.size();
			}

			@Override
			public Object getItem(int position) {
				
				return listCityDatas.get(position);
			}

			@Override
			public long getItemId(int position) {
				
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				Holder holder;
				if (convertView==null) {
					holder=new Holder();
					convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_city_list_item, null);
			      ViewUtils.inject(holder, convertView);
			      convertView.setTag(holder);
				}
				else {
					holder=(Holder) convertView.getTag();
				}
				//数据显示处理
				City city=listCityDatas.get(position);
				String sort=city.getCity_sortkey();
				String name=city.getCity_name();
			//	Log.i("TAG",sort+"="+name);
				if (buffer.indexOf(sort)==-1) {
					buffer.append(sort);
					firstList.add(name);
				}
				if (firstList.contains(name)) {
					holder.keySort.setText(sort);
					holder.keySort.setVisibility(View.VISIBLE);
				}
				else {
					holder.keySort.setVisibility(View.GONE);
				}
				holder.keySort.setText(sort);
				holder.cityName.setText(name);
				return convertView;
			}
			
		}
		public class Holder{
			@ViewInject(R.id.city_list_item_sort)
			public TextView keySort;
			@ViewInject(R.id.city_list_item_name)
			public TextView cityName;
			
		}
		
	}


	@Override
	public void OnTouchingLetterChanged(String s) {
		//找到listview中显示的索引位置
   listDatas.setSelection(findIndex(cityList, s));
		
		
	}
	public int findIndex(List<City> list,String s){
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				City city=list.get(i);
				//根据city中的sortkey进行比较
				if (s.equals(city.getCity_sortkey())) {
					return i;
				}
				
				
			}
		}else {
			Toast.makeText(getApplication(), "暂无信息", 1).show();
		}
		return -1;
		
	}
	
	
}

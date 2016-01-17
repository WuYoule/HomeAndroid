package com.wqy.dianping;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wqy.consts.CONSTS;
import com.wqy.entity.Categroy;
import com.wqy.entity.City;
import com.wqy.entity.ResponseObject;
import com.wqy.utils.MyUtils;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Entity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//全部分类
public class AllCategroyActivity extends Activity {
	@ViewInject(R.id.home_nav_all_categroy)
	private ListView categroyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_index_nav_all);
		ViewUtils.inject(this);
	   new CategroyDataTask().execute();
		
	}
	
	//使用异步任务获取信息
	public class CategroyDataTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			HttpClient client=new DefaultHttpClient();
			HttpGet get=new HttpGet(CONSTS.Categroy_Data_URI);
			try {
				HttpResponse response=client.execute(get);
				if (response.getStatusLine().getStatusCode()==200) {
					String json= EntityUtils.toString(response.getEntity());
					parseCategroyDataJosn(json);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//适配
			categroyList.setAdapter(new MyAdapter());
		}
		
	}
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MyUtils.allCategroy.length;
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
				convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_index_nav_all_item, null);
				ViewUtils.inject(holder, convertView);
				convertView.setTag(holder);
			}
			else {
				holder=(MyHolder) convertView.getTag();
			}
			holder.desc.setText(MyUtils.allCategroy[position]);
			holder.img.setImageResource(MyUtils.allCategroyImages[position]);
			holder.number.setText(MyUtils.allCategroyNumber[position]+"");
			return convertView;
		}
		
		
	}
	public class MyHolder{
		@ViewInject(R.id.home_nav_all_item_number)
		public TextView number;
		@ViewInject(R.id.home_nav_all_item_desc)
		public TextView desc;
		@ViewInject(R.id.home_nav_all_item_image)
		public ImageView img;
		
	}
	
	@OnClick(R.id.home_nav_all_back)
	public void OnClick(View view){
		switch (view.getId()) {
		case R.id.home_nav_all_back://点击返回
			finish();
			break;

		default:
			break;
		}
	}

	public void parseCategroyDataJosn(String json) {
		Gson gson=new Gson();
		Type listType=new TypeToken<ResponseObject<List<Categroy>>>(){}.getType();
		ResponseObject<List<Categroy>> responseObjectList=gson.fromJson(json,listType);
		List<Categroy>  categroys=responseObjectList.getDatas();
		for (Categroy categroy : categroys) {
			int position=Integer.parseInt(categroy.getCategroyId());
			MyUtils.allCategroyNumber[position-1]=categroy.getCategroyNumber();
		}
		
	}
	
}

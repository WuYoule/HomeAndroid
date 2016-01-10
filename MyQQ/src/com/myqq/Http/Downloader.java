package com.myqq.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import com.google.gson.reflect.TypeToken;
import com.myqq.model.UserInfo;

import adapter.UserAdapter;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class Downloader extends AsyncTask<String, Void, String> {
    private URL url;
    private HttpURLConnection connection;
    private ListView listView;
    private Context context;
    
	
    public  Downloader(ListView listView,Context context) {
	  this.listView=listView;
	  this.context=context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String line = null;
		StringBuffer response = null;
		BufferedReader bufferedReader = null;
		try {
			url=new URL("http://192.168.1.112:8080/myqq/text.json");
			connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			InputStream inputStream=connection.getInputStream();
			
			 bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
			response=new StringBuffer();
		
			while ((line=bufferedReader.readLine())!=null) {
				
				response.append(line);
			}
			Log.i("TAG", response.length()+"");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		finally{
			try {
				bufferedReader.close();
				connection.disconnect();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
			
		}
		return response.toString();
	}
	@Override
	protected void onPostExecute(String result) {
		
		Log.i("TAG", result);
		Gson gson=new Gson();
		Type listType=new TypeToken<List<UserInfo>>(){}.getType();
		//UserInfo wrap=gson.fromJson(result, UserInfo.class);
		
		List<UserInfo> list=gson.fromJson(result, listType);
		Log.i("TAG", list.size()+"");
		
		
		
	UserAdapter userAdapter=new UserAdapter(list, context);	
		listView.setAdapter(userAdapter);
		
		
	}

}

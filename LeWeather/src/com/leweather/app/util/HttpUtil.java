package com.leweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import android.R.string;
import android.util.Log;

public class HttpUtil {
        public static void sendHttpRequest(final String address,final HttpCallbackListener listener)
        
        {
        	Log.i("Youle1", address);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					HttpURLConnection connection=null;
						try {
							URL url=new URL(address);
							connection = (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");
							connection.setConnectTimeout(8000);
							connection.setReadTimeout(8000);
							InputStream in=connection.getInputStream();
							BufferedReader reader=new BufferedReader(new InputStreamReader(in));
							StringBuffer response=new StringBuffer();
							
							String line;
							while ((line=reader.readLine())!=null) {
								response.append(line);
								
							}
							//Log.i("Youle2", line);
							if (listener!=null) {
								//回调onFinish()方法
								listener.onFinish(response.toString());
							}
							
							
						} catch (Exception e) {
							if (listener!=null) {
								Log.i("Youle error", e.toString());
								//回调onError()方法
								listener.onError(e);
							}
						}
						finally
						{
							if (connection!=null) {
								connection.disconnect();
							}
							
						}
					
				}
			}).start();
		}
	
	
}

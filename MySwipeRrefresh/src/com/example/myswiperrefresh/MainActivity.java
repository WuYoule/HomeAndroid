package com.example.myswiperrefresh;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private PullToRefreshListView mPullRefreshListView;
	ArrayList<String> mArrayList=new ArrayList<String>();
	private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
    	
    	//设置PullRefreshListView上提加载时的加载提示
    			mPullRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
    			mPullRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
    			mPullRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
    	        // 设置PullRefreshListView下拉加载时的加载提示
    			mPullRefreshListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
    			mPullRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新...");
    			mPullRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新...");
    			
    	mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});
    	mAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);
        mPullRefreshListView.setAdapter(mAdapter);
    
    }
    
    private int index=1;
    private String getContent() {
		String conString= "hello world!"+String.valueOf(index);
		index++;
		return conString;
	}
    private class GetDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return getContent();
		}

		@Override
		protected void onPostExecute(String result) {
			
			mArrayList.add(result);
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}

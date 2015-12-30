package com.example.bdwall;

import java.util.ArrayList;

import com.example.bdwall.PullLoadRefreshView.IPullCallBack;
import com.example.bdwall.R.drawable;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SelectFragment extends Fragment {

	PullLoadRefreshView pullLoadRefreshView;
	MyGridView gridView;
	myAdapter mAdapter;
	ArrayList<Integer> dataArrayList = new ArrayList<Integer>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.select_fragment, container, false);
		findView(view);
		return view;
	}

	private void findView(View view) {
		pullLoadRefreshView = (PullLoadRefreshView) view
				.findViewById(R.id.pullloadrefreshview);
		gridView = (MyGridView) view.findViewById(R.id.plr_gridview);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		init();
	}

	private void init() {
		pullLoadRefreshView.setIPullClick(new MyCallBack());
		initGridData();
		mAdapter = new myAdapter(getActivity());
		gridView.setAdapter(mAdapter);
	}

	// gridview 數據初始化

	private void initGridData() {
		Log.i("mylog", dataArrayList.size()+"");
		for (int i = 0; i < 10; i++) {
			dataArrayList.add(R.drawable.img1);
		}

	}

	private class MyCallBack implements IPullCallBack {

		@Override
		public void load() {// 加載數據
			
			
			Log.i("mylog", dataArrayList.size()+"");
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						Thread.sleep(1000);
						initGridData();
						

						handler.sendEmptyMessage(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}
			}).start();

		}

		@Override
		public void refresh() {// 刷新數據

		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			switch (message.what) {
			case 100:// 数据加载完成 需要重新适配
				mAdapter.notifyDataSetChanged();
				pullLoadRefreshView.dataFinsh();

				break;

			default:
				break;
			}

		}

	};

	private class myAdapter extends BaseAdapter {
		Context context;
		LayoutInflater inflater;

		public myAdapter(Context context) {
			this.context = context;
			inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataArrayList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return dataArrayList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.select_fragment_grid_item, null);
				viewHolder = new ViewHolder();
				viewHolder.img = (ImageView) convertView
						.findViewById(R.id.sfgi_img);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();

			}
			viewHolder.img.setBackgroundResource(dataArrayList.get(position));
			return convertView;
		}

	}

	static class ViewHolder {
		ImageView img;

	}

}

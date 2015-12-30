package com.example.bdwall;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class ShowActivity extends Activity {

	Gallery myGallery;
	
	ArrayList<Integer> bitMapData=new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_show);
		myGallery=(Gallery) findViewById(R.id.myGallery);
		initData();
		myGallery.setAdapter(new MyAdapter(this));
	}

	private void initData() {
		bitMapData.add(R.drawable.img1);
		bitMapData.add(R.drawable.img2);
		bitMapData.add(R.drawable.img3);
		bitMapData.add(R.drawable.img4);
		
	}

	private class MyAdapter extends BaseAdapter{
		Context context;
		LayoutInflater inflater;

		public MyAdapter(Context context){
			this.context=context;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return bitMapData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return bitMapData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView img=new ImageView(context);
			img.setBackgroundResource(bitMapData.get(position));
			img.setLayoutParams(new Gallery.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			
			return img;
		}
		
		
	}
	
	
}

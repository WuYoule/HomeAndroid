package com.myqq.start;

import com.example.myqq.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MyqqStartActivtiy extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.start_activity);
    	new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
			    startActivity(new Intent(MyqqStartActivtiy.this, ViewPagerActivity.class));
				
			}
		}, 2000);
    }

}

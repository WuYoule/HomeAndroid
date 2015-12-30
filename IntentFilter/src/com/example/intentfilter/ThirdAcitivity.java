package com.example.intentfilter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ThirdAcitivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	   Log.i("ThirdActivity", "onCreate");   
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_layout);
	}
     
}
